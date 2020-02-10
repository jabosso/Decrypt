//
// Created by jabosso on 16/01/20.
//

#include "database.h"
#include <iostream>
#include <vector>
#include <fstream>

using namespace std;
vector<string> load_database(){
    string line;
    int righ=0;
    fstream file, file2;
    file.open ("dataset.csv", ios::in);
    while(getline (file,line)){
        righ++;
    }
    vector<string> datas;
    file.close();
    file2.open ("dataset.csv", ios::in);
    int i=0;
    while(getline (file2,line)){
        datas.push_back(line);
        i++;
    }
    file.close();
    return datas;


}
