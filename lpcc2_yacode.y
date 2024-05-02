%{
	#include <stdio.h>
	int flag = 0;
%}

%token NUMBER

%left '+' '-'
%left '*' '/' '%'
%left '(' ')'

%%

ArithmeticExpressio : E{
	printf("\nResult = %d\n", $$);
	return 0;
	}

E: E'+'E {$$ = $1 + $3;}
 | E'-'E {$$ = $1 - $3;}
 | E'*'E {$$ = $1 * $3;}
 | E'/'E {$$ = $1 / $3;}
 | E'%'E {$$ = $1 % $3;}
 | '('E')' {$$ = $2;}
 | NUMBER {$$ = $1;}
;
%%

void main()
{
	printf("Enter Arithmetic Expression : ");
	yyparse();

	if (flag == 0)
	{
		printf("\nArithmetic expression is Valid\n");
	}
}

void yyerror()
{
	printf("Arithmetic expression is invalid\n");
	flag = 1;
}