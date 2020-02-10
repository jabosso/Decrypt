//
// Created by jabosso on 07/01/20.
//

#ifndef PSW_OPM_KEY_TRASFORMER_H
#define PSW_OPM_KEY_TRASFORMER_H

#include <iostream>
#include <cmath>
#include <bits/stdc++.h>
#include <stdio.h>
#include <omp.h>
#include "des.h"


#define num 56

using namespace std;



struct table{
    int shift_table[16]=
            { 1, 1, 2, 2,
              2, 2, 2, 2,
              1, 2, 2, 2,
              2, 2, 2, 1
            };
    int key_comp[48]=
            { 14,17,11,24,1,5,
              3,28,15,6,21,10,
              23,19,12,4,26,8,
              16,7,27,20,13,2,
              41,52,31,37,47,55,
              30,40,51,45,33,48,
              44,49,39,56,34,53,
              46,42,50,36,29,32
            };

};



struct key_set{
    vector<string> rkb;
    vector<string> rk;
};


key_set key_producer(long int x);

#endif //PSW_OPM_KEY_TRASFORMER_H
