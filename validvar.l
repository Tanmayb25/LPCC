%{
#include "y.tab.h"
%}

%%
[a-zA-Z]   { return A; }
[0-9]      { return N; }
_          { return U; }
\n         { return 0; }
.          { return yytext[0]; }
%%

int yywrap() {
    return 1;
}
