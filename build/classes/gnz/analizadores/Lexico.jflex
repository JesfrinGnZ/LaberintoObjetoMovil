package analizadores; // Paquete donde estará nuestro scanner
import java_cup.runtime.Symbol; 
import objetomovil4.*;
import objetos.Movimiento;
%% 

%{
    /*Area de declaración de codigo java. 
    *Funciones y variables necesarias
    */
    String nombre;
    public void imprimir(String cadena)
    {
    	System.out.println(cadena);
    }
%}

// Area de directivas
%cupsym sym1 
%class Lexico
%cup
%public
%unicode
%line
%column
%char
%ignorecase


// Area de definición de expresiones regulares. Tokens.
espacio = \t|\f|" "|\r|\n
numerico =["-"]?[0-9]+ | ["-"]?[0-9]+ "." [0-9]+
id=[A-Za-z]+["_"0-9A-Za-z]*

%%
// Acciones
","                 {return new Symbol(sym1.com,yyline,yycolumn,yytext());}
"("                 {return new Symbol(sym1.Para,yyline,yycolumn,yytext());}
")"                 {return new Symbol(sym1.Parc,yyline,yycolumn,yytext());}
"Ini"               {return new Symbol(sym1.RIni,yyline,yycolumn,yytext());}
"Fin"               {return new Symbol(sym1.RFin,yyline,yycolumn,yytext());}
"trayecto"          {return new Symbol(sym1.RTra,yyline,yycolumn,yytext());}
"MovRecto"          {return new Symbol(sym1.RMre,yyline,yycolumn,yytext());}
"MovDiagonal"       {return new Symbol(sym1.RMdi,yyline,yycolumn,yytext());}
"adelante"          {return new Symbol(sym1.RAde,yyline,yycolumn,yytext());}
"atras"             {return new Symbol(sym1.RAtr,yyline,yycolumn,yytext());}
"arriba"            {return new Symbol(sym1.RArr,yyline,yycolumn,yytext());}
"abajo"             {return new Symbol(sym1.RAba,yyline,yycolumn,yytext());}
"cm"                {return new Symbol(sym1.Rcm,yyline,yycolumn,yytext());}
"grados"            {return new Symbol(sym1.RGra,yyline,yycolumn,yytext());}
{espacio}           {/*Ignorar*/}
{numerico}          {return new Symbol(sym1.numerico,yyline,yycolumn,yytext());}


//Recolección de errores
.       {   
        
        System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
}