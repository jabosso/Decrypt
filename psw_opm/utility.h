//
// Created by jabosso on 12/12/19.
//

#ifndef PC1_UTILITY_H
#define PC1_UTILITY_H
#include <iostream>
#include <vector>

using namespace std;
vector<string> genDict(){
    vector<string> dict(6);
    dict[0]="03/05/94";
    dict[1]="19/09/92";
    dict[2]="23/12/59";
    dict[3]="13/12/98";
    dict[4]="10/08/58";
    dict[5]="24/11/92";
    return dict;
}
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

#endif //PC1_UTILITY_H
