@echo on
echo ...cleanup
@echo off

REM Cleanup von alten Dateien
del *.lyx /s /q
del *.pdf /s /q
del html\*.* /s /q
del tex\*.* /s /q
del tmp\*.* /s /q

@echo on
echo ...convert *.wiki to .html
@echo off

cd bin\wiki2html\
ruby wiki_convertor.rb ../../ ../../html/

@echo on
echo ...convert *.html to .tex
@echo off

cd ..\html2tex\
FOR %%a in (../../html/*.html) do java -jar htmltolatex.jar -input ../../html/%%a -output ../../tex/%%a.tex

@echo on
17:48 04.03.2010...convert *.tex to .lyx
@echo off

cd ..\tex2lyx\bin\
FOR %%a in (../../../tex/*.tex) do tex2lyx.exe ../../../tex/%%a ../../../%%a.lyx -f

@echo on
echo ...convert *.tex to .pdf
@echo off

cd ..
cd ..
cd ..
FOR %%a in (tex/*.tex) do pdflatex.exe tex/%%a %%a.pdf -interaction=batchmode -quiet -aux-directory=tmp/

@echo on
echo job done! byebye!
@echo off