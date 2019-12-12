#include <iostream>
#include <cmath>
#include <omp.h>
#include <bits/stdc++.h>
#include "text_conv.h"
#include "des.h"
#define  num 56
using namespace std;

void researchbykey(string key_bin, string target){
    string plain_text[6] = {"03/05/94",
                             "19/09/92",
                             "23/12/59",
                             "13/12/98",
                             "10/08/58",
                             "24/11/92"};
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

    string left = key_bin.substr(0,28);
    string right =key_bin.substr(28,28);
    vector<string> rkb;
    vector<string>rk;
//---------------------------------------------------------------------------------------------------//
    for(int i=0; i<16; i++){
        left = shift_left(left,shift_table[i]);
        right = shift_left(right,shift_table[i]);
        string combine = left+right;
        string RoundKey = permute(combine, key_comp, 48);
        rkb.push_back(RoundKey);
        rk.push_back(bin2hex(RoundKey));
    }
//---------------------------------------------------------------------------------------------------//
    for (int r=0;r<6;r++){
        string cipher = encrypt(chr2hex(plain_text[r]), rkb,rk);
        if (cipher == target){
            cout<<"la psw in chiaro è "<<plain_text[r]<<endl;
            cout<<"corrispondenza con "<<cipher<<endl;
            break;

        }

    }



}


void keyIterator(long int n, string target){

#pragma omp parallel for
    for (long int i=0; i<n;i++){

        bitset<num>binary(i) ;
           // cout <<"  "<<binary<<"   "<< endl;
            string x = binary.to_string();
            researchbykey(x, target);

        }


}

int main()
{
    bitset<num>original_key_bin(34);
    string plain_psw = "13/12/98";
    string originalkey = original_key_bin.to_string();
    cout<< "la chiave originale :  " << originalkey << endl;
    cout<< "psw in chiaro  : "<< plain_psw << endl;
//--------------------------------------------------------------------------------------------------------------------//
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

    string left = originalkey.substr(0,28);
    string right =originalkey.substr(28,28);
    vector<string> rkb;
    vector<string>rk;
//---------------------------------------------------------------------------------------------------//
    for(int i=0; i<16; i++){
        left = shift_left(left,shift_table[i]);
        right = shift_left(right,shift_table[i]);
        string combine = left+right;
        string RoundKey = permute(combine, key_comp, 48);
        rkb.push_back(RoundKey);
        rk.push_back(bin2hex(RoundKey));
    }
//--------------------------------------------------------------------------------------------------------------------//
    string encoded_psw = encrypt(chr2hex(plain_psw), rkb,rk);
    cout<< "psw cifrata  : "<< encoded_psw << endl;
    keyIterator(pow(2,num),encoded_psw);
    return 0;
}
