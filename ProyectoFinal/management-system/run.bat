@echo off
echo Compilando y ejecutando el Sistema de Gestion de Libros...
echo.

REM Create necessary directories
if not exist target\classes mkdir target\classes
if not exist data mkdir data

REM Clean previous compilation
del /Q /S target\classes\* 2>nul

REM Set source directory
set "SRC_DIR=src\main\java"

REM Compile all Java files
echo Compilando archivos Java...
javac -d target\classes -sourcepath %SRC_DIR% %SRC_DIR%\com\management\App.java %SRC_DIR%\com\management\util\Storage.java %SRC_DIR%\controller\MainController.java %SRC_DIR%\model\*.java %SRC_DIR%\view\*.java

REM Compile all Java files
echo Compiling Java files...
javac -d target\classes src\main\java\com\management\*.java src\main\java\com\management\util\*.java src\main\java\controller\*.java src\main\java\model\*.java src\main\java\view\*.java2>&1

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation failed! Please check the errors above.
    pause
    exit /b %ERRORLEVEL%
)

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo Error de compilacion! Por favor revise los errores.
    pause
    exit /b %ERRORLEVEL%
)

REM Run the application
echo.
echo Iniciando la aplicacion...
java -cp target\classes com.management.App

pause
