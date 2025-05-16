# Script de compilación y ejecución

Write-Host "Compilando y ejecutando el Sistema de Gestión de Libros..." -ForegroundColor Green

# Crear directorios necesarios
$directories = @("target/classes", "data", "lib")
foreach ($dir in $directories) {
    if (-not (Test-Path $dir)) {
        New-Item -ItemType Directory -Path $dir -Force | Out-Null
        Write-Host "Creado directorio: $dir" -ForegroundColor Yellow
    }
}

# URLs de las dependencias
$dependencies = @(
    @{
        name = "jackson-databind-2.15.2.jar"
        url = "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar"
    },
    @{
        name = "jackson-core-2.15.2.jar"
        url = "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar"
    },
    @{
        name = "jackson-annotations-2.15.2.jar"
        url = "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar"
    }
)

# Descargar dependencias
foreach ($dep in $dependencies) {
    $jarPath = "lib/$($dep.name)"
    if (-not (Test-Path $jarPath)) {
        Write-Host "Descargando $($dep.name)..." -ForegroundColor Cyan
        try {
            Invoke-WebRequest -Uri $dep.url -OutFile $jarPath
        }
        catch {
            Write-Host "Error descargando $($dep.name): $_" -ForegroundColor Red
            exit 1
        }
    }
}

# Limpiar compilación anterior
if (Test-Path "target/classes") {
    Remove-Item -Path "target/classes/*" -Recurse -Force
}

# Construir classpath
$classPath = "target/classes;lib/jackson-databind-2.15.2.jar;lib/jackson-core-2.15.2.jar;lib/jackson-annotations-2.15.2.jar"

# Compilar
Write-Host "`nCompilando archivos Java..." -ForegroundColor Yellow
$compileResult = javac -d "target/classes" -cp $classPath -sourcepath "src/main/java" src/main/java/com/management/App.java 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host "Error de compilación:" -ForegroundColor Red
    Write-Host $compileResult -ForegroundColor Red
    exit 1
}

# Ejecutar la aplicación
Write-Host "`nIniciando la aplicación..." -ForegroundColor Green
java -cp $classPath com.management.App

if ($LASTEXITCODE -ne 0) {
    Write-Host "`nLa aplicación terminó con errores." -ForegroundColor Red
}

Write-Host "`nPresione cualquier tecla para continuar..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
