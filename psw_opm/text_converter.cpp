//
// Created by jabosso on 07/01/20.
//
#include "text_converter.h"
#include <map>

string hex2bin(string s){
    map<char, string> mph2b;
    mph2b['0']= "0000";
    mph2b['1']= "0001";
    mph2b['2']= "0010";
    mph2b['3']= "0011";
    mph2b['4']= "0100";
    mph2b['5']= "0101";
    mph2b['6']= "0110";
    mph2b['7']= "0111";
    mph2b['8']= "1000";
    mph2b['9']= "1001";
    mph2b['A']= "1010";
    mph2b['B']= "1011";
    mph2b['C']= "1100";
    mph2b['D']= "1101";
    mph2b['E']= "1110";
    mph2b['F']= "1111";

    string bin="";
    for (int i=0; i<s.size();i++){
        bin+= mph2b[s[i]];
    }
    return bin;
}

string bin2hex(string s){
    // binary to hexadecimal conversion
    map<string, string> mpb2h;
    mpb2h["0000"]= "0";
    mpb2h["0001"]= "1";
    mpb2h["0010"]= "2";
    mpb2h["0011"]= "3";
    mpb2h["0100"]= "4";
    mpb2h["0101"]= "5";
    mpb2h["0110"]= "6";
    mpb2h["0111"]= "7";
    mpb2h["1000"]= "8";
    mpb2h["1001"]= "9";
    mpb2h["1010"]= "A";
    mpb2h["1011"]= "B";
    mpb2h["1100"]= "C";
    mpb2h["1101"]= "D";
    mpb2h["1110"]= "E";
    mpb2h["1111"]= "F";
    string hex="";

    for(int i=0; i<s.length(); i+=4){
        string ch="";
        ch+= s[i];
        ch+= s[i+1];
        ch+= s[i+2];
        ch+= s[i+3];
        hex+= mpb2h[ch];
    }

    return hex;
}

string chr2hex(string s){
    map<char,string>mp;
    mp['A']= "41";
    mp['B']= "42";
    mp['C']= "43";
    mp['D']="44";
    mp['E']="45";
    mp['F']="46";
    mp['G']="47";
    mp['H']="48";
    mp['I']="49";
    mp['J']="4A";
    mp['K']="4B";
    mp['L']="4C";
    mp['M']="4D";
    mp['N']="4E";
    mp['O']="4F";
    mp['P']="50";
    mp['Q']="51";
    mp['R']="52";
    mp['S']="53";
    mp['T']="54";
    mp['U']="55";
    mp['V']="56";
    mp['W']="57";
    mp['X']="58";
    mp['Y']="59";
    mp['Z']="5A";
    mp['a']="61";
    mp['b']="62";
    mp['c']="63";
    mp['d']="64";
    mp['e']="65";
    mp['f']="66";
    mp['g']="67";
    mp['h']="68";
    mp['i']="69";
    mp['j']="6A";
    mp['k']="6B";
    mp['l']="6C";
    mp['m']="6D";
    mp['n']="6E";
    mp['o']="6F";
    mp['p']="70";
    mp['q']="71";
    mp['r']="72";
    mp['s']="73";
    mp['t']="74";
    mp['u']="75";
    mp['v']="76";
    mp['w']="77";
    mp['x']="78";
    mp['y']="79";
    mp['z']="7a";
    mp['0']="30";
    mp['1']="31";
    mp['2']="32";
    mp['3']="33";
    mp['4']="34";
    mp['5']="35";
    mp['6']="36";
    mp['7']="37";
    mp['8']="38";
    mp['9']="39";
    mp['.']="2E";
    mp['/']="2F";
    string hex="";
    for (int i = 0; i < s.size(); i++) {
        hex += mp[s[i]];
    }
    return hex;
}

string hex2chr(string s){
    map<string,string>mp;
    mp["41"]="A" ;
    mp["42"]="B" ;
    mp["43"]="C" ;
    mp["44"]="D";
    mp["45"]="E";
    mp["46"]="F";
    mp["47"]="G";
    mp["48"]="H";
    mp["49"]="I";
    mp["4a"]="J";
    mp["4b"]="K";
    mp["4d"]="M";
    mp["4e"]="N";
    mp["4f"]="O";
    mp["50"]="P";
    mp["51"]="Q";
    mp["52"]="R";
    mp["53"]="S";
    mp["54"]="T";
    mp["55"]="U";
    mp["56"]="V";
    mp["57"]="W";
    mp["58"]="X";
    mp["59"]="Y";
    mp["5a"]="Z";
    mp["61"]="a";
    mp["62"]="b";
    mp["63"]="c";
    mp["64"]="d";
    mp["65"]="e";
    mp["66"]="f";
    mp["67"]="g";
    mp["68"]="h";
    mp["69"]="i";
    mp["6a"]="j";
    mp["6b"]="k";
    mp["6c"]="l";
    mp["6d"]="m";
    mp["6e"]="n";
    mp["6f"]="o";
    mp["70"]="p";
    mp["71"]="q";
    mp["72"]="r";
    mp["73"]="s";
    mp["74"]="t";
    mp["75"]="u";
    mp["76"]="v";
    mp["77"]="w";
    mp["78"]="x";
    mp["79"]="y";
    mp["7a"]="z";
    mp["30"]="0";
    mp["31"]="1";
    mp["32"]="2";
    mp["33"]="3";
    mp["34"]="4";
    mp["35"]="5";
    mp["36"]="6";
    mp["37"]="7";
    mp["38"]="8";
    mp["39"]="9";
    mp["2e"]=".";
    mp["2f"]="/";
    string hex="";
    for (int i = 0; i < s.size(); i=i+2) {
        string ch="";
        ch+=s[i];
        ch+=s[i+1];
        hex += mp[ch];
    }
    return hex;
}