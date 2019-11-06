# Parallel Computing 


Per la realizzazione in parallelo, il progetto potrebbe sfruttare il pattern Producer-Consumers in cui il ruolo di Producer sarebbe svolto da un thread che genera password nel formato descritto aggiungendole ad un array mentre i Consumers sarebbero dei thread che prendono ad ogni iterazione una delle password dall'array, realizzano la stringa criptata applicando l'algoritmo DES ed effettuano il confronto con la stringa generata all'avvio del programma dalla password da decodificare.
Per la realizzazione in serie, il dizionario delle password si genera all'avvio del programma ed in seguito si usa in un ciclo sulla sua lunghezza. Ad ogni iterazione si produce la stringa criptata relativa alla password che si sta analizzando e si confronta con la stringa generata inzialmente relativa alla password da decriptare.
L'idea sarebbe di utilizzare la libreria javax.crypto per generare le stringhe criptate.
