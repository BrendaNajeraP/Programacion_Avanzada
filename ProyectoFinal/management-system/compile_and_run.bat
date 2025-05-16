@echo off
echo Compilando y ejecutando el Sistema de Gestion de Libros...
echo.

REM Create necessary directories
if not exist target\classes mkdir target\classes
if not exist data mkdir data
if not exist lib mkdir lib

REM Download Jackson dependencies if not exist
echo Descargando dependencias de Jackson...
powershell -Command "& {
    $urls = @(
        'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar',
        'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar',
        'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar'
    )
    foreach ($url in $urls) {
        $fileName = Split-Path $url -Leaf
        $outFile = Join-Path 'lib' $fileName
        if (-not (Test-Path $outFile)) {
            Write-Host ('Descargando ' + $fileName + '...')
            Invoke-WebRequest -Uri $url -OutFile $outFile
        }
    }
}"

REM Clean previous compilation
del /Q /S target\classes\* 2>nul

REM Set classpath with explicit jar files
set "CLASSPATH=.;target/classes;lib/jackson-databind-2.15.2.jar;lib/jackson-core-2.15.2.jar;lib/jackson-annotations-2.15.2.jar"

REM Compile
echo Compilando archivos Java...
javac -d target/classes -cp "%CLASSPATH%" -sourcepath src/main/java src/main/java/com/management/App.java

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo Error de compilacion! Por favor revise los errores.
    pause
    exit /b %ERRORLEVEL%
)

REM Run the application
echo.
echo Iniciando la aplicacion...
java -cp ".;target/classes;lib/jackson-databind-2.15.2.jar;lib/jackson-core-2.15.2.jar;lib/jackson-annotations-2.15.2.jar" com.management.App

pause
