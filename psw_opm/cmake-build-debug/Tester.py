import os
if __name__ == "__main__":
    for i in range(5):
        for j in range(5):
            key_set_dim = 1000*(i+1)
            dataset_dim = 1000*(j+1)            
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim/9))
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim/7))
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim/5))
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim/3))
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim-key_set_dim/3))
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim-key_set_dim/5))
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim-key_set_dim/7))
            os.system("./psw_opm %d %d %d" %( key_set_dim, dataset_dim,key_set_dim-key_set_dim/9))




