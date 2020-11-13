#include <stdio.h>
#include "myMath.h"
double power(double x, int y){
	if(y==0)
		return 1;
	double pow = power(x, y/2);
	if(y%2==0)
		return pow*pow;
	else{
		if(y>0)
			return x*pow*pow;
		else
			return (pow*pow)/x;
	}
}
double Exponent(int x){
	double e=2.71828;
	return (power(e,x));
	}
