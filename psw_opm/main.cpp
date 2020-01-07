#include <iostream>
#include "key_trasformer.h"
#include "des.h"
#include "text_converter.h"

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
time_t  start, fine;
int main() {

    key_set original_key = key_producer(4343422);
    string plain_psw = "13/12/98";
    string encoded_psw = encrypt(chr2hex(plain_psw),original_key.rkb, original_key.rk);
    vector<string>plain_text = genDict();
    long int max_key = pow(2,56);
    time(&start);
#pragma omp parallel for schedule (dynamic, 1000)
    for(long int i=0; i< max_key; i++){
        key_set current_key = key_producer(i);
#pragma omp parallel for schedule (dynamic, 2)
        for (int r=0;r<6; r++){
            string cipher = encrypt(chr2hex(plain_text[r]),current_key.rkb, current_key.rk);
            if (cipher ==encoded_psw){
                char p_l[plain_text[r].size()+1];
                strcpy(p_l,plain_text[r].c_str());
                char c_l[cipher.size()+1];
                strcpy(c_l,cipher.c_str());
                printf("la pasword in chiaro è %s codificata in %s \n", p_l, c_l);
                time(&fine);
                double time_taken = double(fine - start);
                cout << "Time taken by program is : " << fixed
                     << time_taken << setprecision(5);
                cout << " sec " << endl;
                exit(EXIT_SUCCESS);
            }
        }
    }

    return 0;
}