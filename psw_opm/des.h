//
// Created by jabosso on 07/01/20.
//

#ifndef DES_FIN_DES_H
#define DES_FIN_DES_H
using namespace std;
string permute(string k, int* arr, int n);
string shift_left(string k, int shifts);
string xor_(string a, string b);
string encrypt(string pt, vector<string> rkb, vector<string> rk);
#endif //DES_FIN_DES_H
