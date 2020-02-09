#include <iostream>
#include "key_trasformer.h"
#include "des.h"
#include "text_converter.h"
#include "database.h"
#include <fstream>
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
int main(int argc, char* argv[]) {

    int key_set_dim;
    int dataset_dim;
    int key_index ;
    if (argc>2){
        key_set_dim = stoi(argv[1]);
        dataset_dim =  stoi(argv[2]);
        key_index   =  stoi(argv[3]);

    }else {
        cout << "insert dim of key_set :";
        cin >> key_set_dim;
        cout << "Insert dim of dataset:";
        cin >> dataset_dim;
        cout << "Insert index of your key:";
        cin >> key_index;
    }
    vector<string> datab =load_database();
    key_set original_key = key_producer(key_index);
    static string plain_psw = "14/01/00";
    string encoded_psw = encrypt(chr2hex(plain_psw),original_key.rkb, original_key.rk);
    vector<string>plain_text = datab; //datab
    long int max_key = pow(2,56);
    time(&start);
//#pragma omp parallel for schedule (dynamic, key_set_dim/4)
    for( int i=0; i< key_set_dim; i++){ //max_key
        key_set current_key = key_producer(i);
//#pragma omp parallel for schedule (dynamic, dataset_dim/4)
        for (int r=0;r<dataset_dim; r++){
            string cipher = encrypt(chr2hex(plain_text[r]),current_key.rkb, current_key.rk);
            if (cipher ==encoded_psw ){
                char p_l[plain_text[r].size()+1];
                strcpy(p_l,plain_text[r].c_str());
                char c_l[cipher.size()+1];
                strcpy(c_l,cipher.c_str());
                // printf("la pasword in chiaro è %s codificata in %s \n", p_l, c_l);
                time(&fine);
                double time_taken = double(fine - start);
                cout << "Time taken by program is : " << fixed
                     << time_taken << setprecision(20);
                cout << " sec " << endl;
                ofstream myfile;
                myfile.open("result.csv",ios::app);
                myfile<<key_set_dim<<","<<dataset_dim<<","<<key_index<<","<< time_taken<<"\n";
                myfile.close();
                exit(EXIT_SUCCESS);
            }
        }
    }

    return 0;
}