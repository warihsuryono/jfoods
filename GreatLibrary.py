from __future__ import print_function

dataRak = []
dataBuku = []
dataLokasiBuku = []

def add_rak(nama):
    if(nama in dataRak):
        return "Rak dengan nama " + nama + " sudah ada di dalam sistem"
    else: 
        dataRak.append(nama)
        return "Rak dengan nama " + nama + " berhasil ditambahkan"

def add_buku(namaRak,namaBuku,pengarang,tahun,penerbit,genre):
    if(namaBuku in dataBuku):
        return "Buku dengan nama [" + namaBuku + ", " + tahun + ", " + penerbit + ", " + genre + "] sudah ada di dalam sistem"
    else: 
        dataBuku.append(nama)
        return "Buku dengan nama [" + namaBuku + ", " + tahun + ", " + penerbit + ", " + genre + "] berhasil ditambahkan"


command = [""]
while command[0].upper() != "EXIT":
    errormessage = "Perintah tidak dikenal!"
    print("Selamat datang di The The Great Library")
    print("Silahkan masukkan perintah!")
    command = input("Perintah anda: ").split(" ");
    if(command[0].upper() == "ADD"):
        if(command[1].upper() == "RAK"):
            errormessage = ""
            try:
                print(add_rak(command[2]));
            except Exception as e: 
                errormessage = "Nama rak tidak boleh kosong!"
            
        if(command[1].upper() == "BUKU"):
            errormessage = ""
            try:
                print(add_buku(command[2],command[2],command[2]));
            except Exception as e: 
                errormessage = "Nama rak tidak boleh kosong!"
    
    if(command[0].upper() == "EXIT"):
        errormessage = ""
        
    print(errormessage)