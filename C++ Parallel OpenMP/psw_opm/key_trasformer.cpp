//
// Created by jabosso on 07/01/20.
//

#include "key_trasformer.h"

#include "text_converter.h"

table mt;

key_set key_producer(long int x){
    bitset<num>original_key_bin(x);
    string originalkey = original_key_bin.to_string();
    string left = originalkey.substr(0,28);
    string right =originalkey.substr(28,28);
    vector<string> rkb;
    vector<string>rk;
    for(int i=0; i<16; i++){
        left = shift_left(left,mt.shift_table[i]);
        right = shift_left(right,mt.shift_table[i]);
        string combine = left+right;
        string RoundKey = permute(combine, mt.key_comp, 48);
        rkb.push_back(RoundKey);
        rk.push_back(bin2hex(RoundKey));
    }
    key_set tmp;
    tmp.rk = rk;
    tmp.rkb = rkb;
    return tmp;
}