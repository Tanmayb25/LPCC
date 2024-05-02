%{
#include <stdio.h>
#include <stdlib.h>
%}

%token A N U

%%

a : A N
  | a A
  | a N
  | a U a
  | A ;

%%

int main() {
    printf("Enter the string: ");
    yyparse();
    printf("Valid variable\n");
    return 0;
}

void yyerror(const char *s) {
    printf("Invalid variable\n");
    exit(1);
}
