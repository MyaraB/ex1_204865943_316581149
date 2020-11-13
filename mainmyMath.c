#include <stdio.h>
#include "myMath.h"
int main(void){
printf("please enter a real number to declare as x for math functions \n ");
double x;
scanf("%lf" , &x);
double ans = Exponent((int)x) + power(x,3) -2;
printf("\n f(x)=e^x + x^3 -2 is equal to %.4lf \n ",ans);
ans = mul(x,3) + mul(power(x,2),2);
printf("f(x)= 3x+2(x^2) is equal to %.4lf \n",ans);
ans = div(mul(power(x,3),4),5) - mul(x,2);
printf("f(x)=(4x^3)/5 -2x is equal to %.4lf \n",ans);
return 0;
}
