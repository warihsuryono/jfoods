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
    if(bukuAda(namaBuku)):
        return "Buku dengan nama [" + namaBuku + ", " + tahun + ", " + penerbit + ", " + genre + "] sudah ada di dalam sistem"
    elif(namaRak not in dataRak): 
        return "Rak dengan nama " + namaRak + " belum terdaftar di dalam sistem"
    else: 
        dataBuku.append([namaBuku,pengarang,tahun,penerbit,genre])
        dataLokasiBuku.append([namaBuku,namaRak])
        return "Buku dengan nama [" + namaBuku + ", " + tahun + ", " + penerbit + ", " + genre + "] berhasil ditambahkan"
        
def move_buku(namaBuku,namaRakBaru):
    if(bukuAda(namaBuku) and namaRakBaru in dataRak):
        countLokasiBuku = len(dataLokasiBuku)
        try:
            i = 0
            while(i < countLokasiBuku):
                if(namaBuku == dataLokasiBuku[i][0]):
                    namaRakLama = dataLokasiBuku[i][1]
                    dataLokasiBuku[i][1] = namaRakBaru
                    return "Buku dengan nama [" + namaBuku + "] dipindahkan dari rak dengan nama [" + namaRakLama + "] ke rak dengan nama [" + namaRakBaru + "]"
                i = i + 1
        except Exception as e:
            return "Pemindahan Buku ada kesalahan, silakan ulangi lagi!"
        
    else:
        return "Buku dengan nama [" + namaBuku + "] dan/atau Rak [" + namaRakBaru + "] tidak ditemukan"
    
        
def bukuAda(namaBuku):
    countBuku = len(dataBuku)
    if(countBuku <= 0):
        return False
    else:
        try:
            i = 0
            while(i < countBuku):
                if(namaBuku == dataBuku[i][0]):
                    return True
                i = i + 1
            return False
        except Exception as e:
            return False
            


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
            
        elif(command[1].upper() == "BUKU"):
            errormessage = ""
            try:
                print(add_buku(command[2],command[3],command[4],command[5],command[6],command[7]));
            except Exception as e:
                print(e)
                errormessage = "Perintah ADD BUKU : ADD BUKU [Nama Rak] [Nama Buku] [Pengarang buku] [Tahun Terbit] [Penerbit] [Genre]"


    if(command[0].upper() == "MOVE"):
        if(command[1].upper() == "BUKU"):
            errormessage = ""
            try:
                print(move_buku(command[2],command[3]));
            except Exception as e: 
                errormessage = "Perintah MOVE BUKU : MOVE BUKU [Nama buku] [Nama Rak baru]"
        
                
    elif(command[0] == "dataRak"):
        print(dataRak)
        
    elif(command[0] == "dataBuku"):
        print(dataBuku)
        
    elif(command[0] == "dataLokasiBuku"):
        print(dataLokasiBuku)
                
    else:
        errormessage = "Perintah tidak dikenal"
    
    if(command[0].upper() == "EXIT"):
        errormessage = ""
    
    if(errormessage != ""):
        print(errormessage)
        
    print("");