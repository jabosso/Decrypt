#include <iostream>
#include <cmath>
#include <bits/stdc++.h>
#include "text_conv.h"
#include "des.h"
#include "utility.h"
#define  num 56
using namespace std;
table mt;
//--------------------------------------------------------------------------------------------------------------------//
void researchbykey(string key_bin, string target){
    vector<string>plain_text = genDict();
    string left = key_bin.substr(0,28);
    string right =key_bin.substr(28,28);
    vector<string> rkb;
    vector<string>rk;
//--------------------------------------------------------------------------------------------------------------------//
    for(int i=0; i<16; i++){
        left = shift_left(left,mt.shift_table[i]);
        right = shift_left(right,mt.shift_table[i]);
        string combine = left+right;
        string RoundKey = permute(combine, mt.key_comp, 48);
        rkb.push_back(RoundKey);
        rk.push_back(bin2hex(RoundKey));
    }
//--------------------------------------------------------------------------------------------------------------------//
    for (int r=0;r<6;r++){
        string cipher = encrypt(chr2hex(plain_text[r]), rkb,rk);
        if (cipher == target){
            cout<<"la psw in chiaro è "<<plain_text[r]<<endl;
            cout<<"corrispondenza con "<<cipher<<endl;
            exit(EXIT_SUCCESS);
        }
    }
}


void keyIterator(long int n, string target){
#pragma omp parallel for
    for (long int i=0; i<n;i++) {
        bitset<num> binary(i);
            // cout <<"  "<<binary<<"   "<< endl;
        string x = binary.to_string();
        researchbykey(x, target);
    }
}

int main()
{
    bitset<num>original_key_bin(12342);
    vector<string>dic = genDict();
    string plain_psw = "13/12/98";
    string originalkey = original_key_bin.to_string();
    cout<< "la chiave originale :  " << originalkey << endl;
    cout<< "psw in chiaro  : "<< plain_psw << endl;
//--------------------------------------------------------------------------------------------------------------------//
    string left = originalkey.substr(0,28);
    string right =originalkey.substr(28,28);
    vector<string> rkb;
    vector<string>rk;
//--------------------------------------------------------------------------------------------------------------------//
    for(int i=0; i<16; i++){
        left = shift_left(left,mt.shift_table[i]);
        right = shift_left(right,mt.shift_table[i]);
        string combine = left+right;
        string RoundKey = permute(combine, mt.key_comp, 48);
        rkb.push_back(RoundKey);
        rk.push_back(bin2hex(RoundKey));
    }
//--------------------------------------------------------------------------------------------------------------------//
    string encoded_psw = encrypt(chr2hex(plain_psw), rkb,rk);
    cout<< "psw cifrata  : "<< encoded_psw << endl;
    keyIterator(pow(2,num),encoded_psw);
    return 0;
}
