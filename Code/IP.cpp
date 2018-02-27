#include <iostream>       
#include <bitset>         
#include <array>         
#include <stdio.h>         

typedef unsigned int uint;
typedef unsigned char byte;

using namespace std;



bitset<32> CreateIP(byte a, byte b, byte c, byte d) {           // ====== CREATE AN IP ====
    uint IPAdress = 0;                                          //Start by cleaning it all
    IPAdress |= a << (32 - 8);                                  //Let`s put a info in 0-7
    IPAdress |= b << (32 - 16);                                 //Let`s put b info in 8-15
    IPAdress |= c << (32 - 24);                                 //Let`s put c info in 16-31
    IPAdress |= d;                                              //The info in d just fit
    
    return bitset<32>(IPAdress);                                //Go little butterfly
}
bitset<32> CreateIP(array<byte, 4> Data) {                      // ====== CREATE AN IP ====
    return CreateIP(Data[0], Data[1], Data[2], Data[3]);        //Return the cool part
}

char GetClass(bitset<32> IP) {                                  // ===== GET IP CLASS ===
    if (IP[31] == 0) return 'A';                                //If start in 0 return a
    else if (IP[30] == 0) return 'B';                           //If start in 10 return b
    else if (IP[29] == 0) return 'C';                           //If start in 110 return c
    else if (IP[28] == 0) return 'D';                           //If start in 1110 return d
    else return 'E';                                            //Else you are just weird
}

string CreateStringView(bitset<32> IP, bool Decimal = true) {   // ===== CREATE STRING VIEW ===

    string Data;                                                //Return data
    for (int i = 0; i < 4; ++i) {                               //For each block of 8 bits

        bitset<8> Block;                                        //Create a new one
        for (int j = 0; j < 8; ++j) Block[j] = IP[(8*i) + j];   //Copy the corresponding bits

        auto Temporal = Decimal?                                //We want decimal notation
            to_string(Block.to_ulong()) :                       //Then show me the string in _10
            Block.to_string();                                  //Else show me the string in _2

        Data.insert(0, (i != 0)? Temporal + "." : Temporal);    //If not end insert + "."
    }

    return Data;                                                //Send the string
}



int main () {

    std::array<byte, 4> Data;

    //cin >> Data[0] >> Data[1] >> Data[2] >> Data[3];
    scanf("%hu.%hu.%hu.%hu", &Data[0], &Data[1], &Data[2], &Data[3]);

    auto IP = CreateIP(Data);

    cout << IP.to_string() << endl;
    cout << CreateStringView(IP) << endl;
    cout << GetClass(IP) << endl;

  return 0;
}