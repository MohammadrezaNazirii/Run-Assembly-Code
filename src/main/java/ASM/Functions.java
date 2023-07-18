package ASM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Functions {

    static Integer[] TwosComp(Integer[] n, int size) {
        for (int i = 0; i < size; ++i) {
            if (n[i] == 0) {
                n[i] = 1;
            } else {
                n[i] = 0;
            }
        }
        for (int i = size - 1; i >= 0; --i) {
            if (n[i] == 1) {
                n[i] = 0;
            } else {
                n[i] = 1;
                break;
            }
        }
        return n;
    }

    static String DecToBin(String m) {
        int n = Integer.parseInt(m);
        Integer[] binaryNum = new Integer[32];
        for (int i = 0; i < 32; ++i) {
            binaryNum[i] = 0;
        }
        Boolean flag = false;

        if (n < 0) {
            n *= -1;
            flag = true;
        }

        int i = 31;
        while (n > 0) {
            binaryNum[i] = n % 2;
            n = n / 2;
            i--;
        }
        if (flag) {
            binaryNum = TwosComp(binaryNum, 32);
        }

        String temp = "";
        for (int j = 0; j < 32; ++j) {
            temp += binaryNum[j]; // 48 +
        }

        return temp;
    }

    static String HexToBin(String m) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < m.length(); ++i) {
            switch (m.charAt(i)) {
                case '0': {
                    res.append("0000");
                    break;
                }
                case '1': {
                    res.append("0001");
                    break;
                }
                case '2': {
                    res.append("0010");
                    break;
                }
                case '3': {
                    res.append("0011");
                    break;
                }
                case '4': {
                    res.append("0100");
                    break;
                }
                case '5' : {
                    res.append("0101");
                    break;
                }
                case '6': {
                    res.append("0110");
                    break;
                }
                case '7': {
                    res.append("0111");
                    break;
                }
                case '8': {
                    res.append("1000");
                    break;
                }
                case '9': {
                    res.append("1001");
                    break;
                }
                case 'a': {
                    res.append("1010");
                    break;
                }
                case 'b': {
                    res.append("1011");
                    break;
                }
                case 'c': {
                    res.append("1100");
                    break;
                }
                case 'd': {
                    res.append("1101");
                    break;
                }
                case 'e': {
                    res.append("1110");
                    break;
                }
                case 'f': {
                    res.append("1111");
                    break;
                }

            }
        }
        return res.toString();
    }

    static void Print(Integer[] reg) {
        for (int i = 0; i < 32; ++i) {
            System.out.print(reg[i]);
        }
        System.out.println();
    }

    static ArrayList<String> Input() {
        String trash = "";
        Boolean flag = false;
        Boolean flag2 = false;
        ArrayList<String> temp = new ArrayList<>();
        try {
            File input = new File("C:\\Users\\ASUS\\IdeaProjects\\assembly\\src\\main\\java\\ASM\\input.txt");
            Scanner myReader = new Scanner(input);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.toLowerCase();
                if(data.contains(";")){
                    data = data.substring(0, data.indexOf(";"));
                }
                data = data.replaceAll("\\s+", " ");
                String[] DATA = data.split(" ");


                for (String i :DATA) {
                    if(flag) {
                        flag = false;
                        trash = i;
                        continue;
                    }
                    if(Objects.equals(i, "comment") && !flag2){
                        flag = true;
                        flag2 = true;
                    }else if(flag2){
                        if(Objects.equals(i, trash)){
                            flag2 = false;
                        }
                    }else {
                        if(!Objects.equals(i, ",") && !Objects.equals(i, "\n") && !Objects.equals(i, "") && !Objects.equals(i, " ")) {
                            boolean flagg = false;
                            String[] strings = null;
                            if(i.contains(",")){
                                int t = i.indexOf(",");
                                if(t == 0) {
                                    i = i.substring(1);
                                    flagg = false;
                                }
                                else if(t == i.length()-1) {
                                    i = i.substring(0, i.length() - 1);
                                    flagg = false;
                                }
                                else{
                                    strings = i.split(",");
                                    flagg = true;
                                }
                            }
                            if(i.contains(":")){
                                i = i.substring(0, i.length()-1);
                            }
//                            System.out.println(i);
                            if(flagg){
                                temp.add(strings[0]);
                                temp.add(strings[1]);
                            }else
                                temp.add(i);
                        }
                    }

                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return temp;
    }

    static String intListToString(Integer[] temp){
        String t = "";
        for(int i=0;i<32;i++)
            t += temp[i];
        return t;
    }

    static ObservableList<Map<String, Object>> GUIView(){
        ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

        items.clear();

        Map<String, Object> eax = new HashMap<>();
        eax.put("Register | Flag", "EAX");
        eax.put("Value" , Functions.intListToString(Main.EAX));
        items.add(eax);

        Map<String, Object> ebx = new HashMap<>();
        ebx.put("Register | Flag", "EBX");
        ebx.put("Value" , Functions.intListToString(Main.EBX));
        items.add(ebx);

        Map<String, Object> ecx = new HashMap<>();
        ecx.put("Register | Flag", "ECX");
        ecx.put("Value" , Functions.intListToString(Main.ECX));
        items.add(ecx);

        Map<String, Object> edx = new HashMap<>();
        edx.put("Register | Flag", "EDX");
        edx.put("Value" , Functions.intListToString(Main.EDX));
        items.add(edx);

        Map<String, Object> esp = new HashMap<>();
        esp.put("Register | Flag", "ESP");
        esp.put("Value" , Functions.intListToString(Main.ESP));
        items.add(esp);

        Map<String, Object> ebp = new HashMap<>();
        ebp.put("Register | Flag", "EBP");
        ebp.put("Value" , Functions.intListToString(Main.EBP));
        items.add(ebp);

        Map<String, Object> esi = new HashMap<>();
        esi.put("Register | Flag", "ESI");
        esi.put("Value" , Functions.intListToString(Main.ESI));
        items.add(esi);

        Map<String, Object> edi = new HashMap<>();
        edi.put("Register | Flag", "EDI");
        edi.put("Value" , Functions.intListToString(Main.EDI));
        items.add(edi);

        Map<String, Object> carry = new HashMap<>();
        carry.put("Register | Flag", "Carry");
        carry.put("Value" , String.valueOf(Main.Carry));
        items.add(carry);

        Map<String, Object> zero = new HashMap<>();
        zero.put("Register | Flag", "Zero");
        zero.put("Value" , String.valueOf(Main.Zero));
        items.add(zero);

        Map<String, Object> sign = new HashMap<>();
        sign.put("Register | Flag", "Sign");
        sign.put("Value" , String.valueOf(Main.Negative));
        items.add(sign);

        Map<String, Object> overflow = new HashMap<>();
        overflow.put("Register | Flag", "Overflow");
        overflow.put("Value" , String.valueOf(Main.Overflow));
        items.add(overflow);

        Map<String, Object> AuxiliaryCarry = new HashMap<>();
        AuxiliaryCarry.put("Register | Flag", "Auxiliary Carry");
        AuxiliaryCarry.put("Value" , String.valueOf(Main.AuxiliaryCarry));
        items.add(AuxiliaryCarry);

        Map<String, Object> parity = new HashMap<>();
        parity.put("Register | Flag", "Parity");
        parity.put("Value" , String.valueOf(Main.Parity));
        items.add(parity);

        return items;
    }

    static void Output(){
        System.out.print("EAX: ");
        Print(Main.EAX);
        System.out.print("EBX: ");
        Print(Main.EBX);
        System.out.print("ECX: ");
        Print(Main.ECX);
        System.out.print("EDX: ");
        Print(Main.EDX);
        System.out.println("Sign Flag: "+Main.Negative);
        System.out.println("Zero Flag: "+Main.Zero);
        System.out.println("Carry Flag: "+Main.Carry);
        System.out.println("Overflow Flag: "+Main.Overflow);
    }
}
