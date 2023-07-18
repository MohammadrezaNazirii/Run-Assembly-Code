package ASM;

public class Instructions {
    static Integer[] firstTemp;
    static Integer[] secondTemp;
    static void GetStandardValue(String first, String second){
        firstTemp = get(first);
        secondTemp = new Integer[get(first).length];
        if(Character.isDigit(second.charAt(0))){ // 8 or 16 or 32 bits
            if(second.charAt(second.length()-1) == 'b'){
                int len = second.length();
                int i=0;
                int j=0;
                for(;i<=(get(first).length - len);i++){
                    secondTemp[i] = 0;
                }
                for(;i<get(first).length;i++){
                    secondTemp[i] = Integer.parseInt(String.valueOf(second.charAt(j)));
                    j++;
                }
            }else if(second.charAt(second.length()-1) == 'h'){
                String j = Functions.HexToBin(second.substring(0, second.length()-1));
                int i = 0;
                int k = 0;
                for(;i<get(first).length - (second.length()-1)*4;i++){
                    secondTemp[i] = 0;
                }
                for(;i<get(first).length;i++){
                    secondTemp[i] = Integer.parseInt(String.valueOf(j.charAt(k)));
                    k++;
                }
            }else{ //decimal
                String j;
                if(second.charAt(second.length()-1) == 'd'){
                    j = Functions.DecToBin(second.substring(0, second.length()-1));
                }else{
                    j = Functions.DecToBin(second);
                }
                int c=31;
                for (int i = get(first).length -1;i>=0;i--){
                    secondTemp[i] = Integer.parseInt(String.valueOf(j.charAt(c)));
                    c--;
                }
            }
        }else{
            System.arraycopy(get(second), 0, secondTemp, 0, 32); // deep copy
//            secondTemp = get(second); // shallow copy ;wrong
        }
    }

    static Integer[] get(String s){
        Integer[] x16 = new Integer[16];
        Integer[] l8 = new Integer[8];
//        Integer[] h8 = new Integer[8];
        switch (s){
            case "eax":
                return Main.EAX;
            case "ax":
                System.arraycopy(Main.EAX, 16, x16, 0, 16);
                return x16;
            case "ah":
                System.arraycopy(Main.EAX, 16, l8, 0, 8);
                return l8;
            case "al":
                System.arraycopy(Main.EAX, 24, l8, 0, 8);
                return l8;
            case "ebx":
                return Main.EBX;
            case "bx":
                System.arraycopy(Main.EBX, 16, x16, 0, 16);
                return x16;
            case "bh":
                System.arraycopy(Main.EBX, 16, l8, 0, 8);
                return l8;
            case "bl":
                System.arraycopy(Main.EBX, 24, l8, 0, 8);
                return l8;
            case "ecx":
                return Main.ECX;
            case "cx":
                System.arraycopy(Main.ECX, 16, x16, 0, 16);
                return x16;
            case "ch":
                System.arraycopy(Main.ECX, 16, l8, 0, 8);
                return l8;
            case "cl":
                System.arraycopy(Main.ECX, 24, l8, 0, 8);
                return l8;
            case "edx":
                return Main.EDX;
            case "dx":
                System.arraycopy(Main.EDX, 16, x16, 0, 16);
                return x16;
            case "dh":
                System.arraycopy(Main.EDX, 16, l8, 0, 8);
                return l8;
            case "dl":
                System.arraycopy(Main.EDX, 24, l8, 0, 8);
                return l8;
            case "esp":
                return Main.ESP;
            case "ebp":
                return Main.EBP;
            case "esi":
                return Main.ESI;
            case "edi":
                return Main.EDI;
        }
        return null;
    }

    static void set(String s, Integer[] temp){
        switch (s){
            case "eax":
                Main.EAX = temp;
                break;
            case "ax":
                System.arraycopy(temp, 0, Main.EAX, 16, 16);
                break;
            case "al":
                System.arraycopy(temp, 0, Main.EAX, 24, 8);
                break;
            case "ah":
                System.arraycopy(temp, 0, Main.EAX, 16, 8);
                break;
            case "ebx":
                Main.EBX = temp;
                break;
            case "bx":
                System.arraycopy(temp, 0, Main.EBX, 16, 16);
                break;
            case "bl":
                System.arraycopy(temp, 0, Main.EBX, 24, 8);
                break;
            case "bh":
                System.arraycopy(temp, 0, Main.EBX, 16, 8);
                break;
            case "ecx":
                Main.ECX = temp;
                break;
            case "cx":
                System.arraycopy(temp, 0, Main.ECX, 16, 16);
                break;
            case "cl":
                System.arraycopy(temp, 0, Main.ECX, 24, 8);
                break;
            case "ch":
                System.arraycopy(temp, 0, Main.ECX, 16, 8);
                break;
            case "edx":
                Main.EDX = temp;
                break;
            case "dx":
                System.arraycopy(temp, 0, Main.EDX, 16, 16);
                break;
            case "dl":
                System.arraycopy(temp, 0, Main.EDX, 24, 8);
                break;
            case "dh":
                System.arraycopy(temp, 0, Main.EDX, 16, 8);
                break;
            case "esp":
                Main.ESP = temp;
                break;
            case "ebp":
                Main.EBP = temp;
                break;
            case "esi":
                Main.ESI = temp;
                break;
            case "edi":
                Main.EDI = temp;
                break;
        }
    }

    static void setZNFlags(String s){
        switch (s){
            case "eax":
            case "ax":
            case "al":
            case "ah":
                int count=0;
                for(int i=0;i<32;i++)
                    if(Main.EAX[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.EAX[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.EAX[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
            case "ebx":
            case "bx":
            case "bl":
            case "bh":
                count=0;
                for(int i=0;i<32;i++)
                    if(Main.EBX[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.EBX[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.EBX[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
            case "ecx":
            case "cx":
            case "cl":
            case "ch":
                count=0;
                for(int i=0;i<32;i++)
                    if(Main.ECX[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.ECX[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.ECX[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
            case "edx":
            case "dx":
            case "dl":
            case "dh":
                count=0;
                for(int i=0;i<32;i++)
                    if(Main.EDX[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.EDX[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.EDX[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
            case "esp":
                count=0;
                for(int i=0;i<32;i++)
                    if(Main.ESP[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.ESP[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.ESP[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
            case "ebp":
                count=0;
                for(int i=0;i<32;i++)
                    if(Main.EBP[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.EBP[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.EBP[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
            case "esi":
                count=0;
                for(int i=0;i<32;i++)
                    if(Main.ESI[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.ESI[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.ESI[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
            case "edi":
                count=0;
                for(int i=0;i<32;i++)
                    if(Main.EDI[i] == 1)
                        count++;
                if(count == 0)
                    Main.Zero = 1;
                else
                    Main.Zero = 0;

                Main.Negative = Main.EDI[0];

                count = 0;
                for(int i=24;i<32;i++)
                    if(Main.EDI[i] == 1)
                        count++;
                if(count%2 == 0)
                    Main.Parity = 1;
                else
                    Main.Parity = 0;
                break;
        }
    }

    static void AND(String first, String second) {
        Integer[] Temp = new Integer[get(first).length];
        GetStandardValue(first, second);
//        Integer[] firstTemp = get(first);
//        Integer[] secondTemp = new Integer[get(first).length];
//        if(Character.isDigit(second.charAt(0))){ // 8 or 16 or 32 bits
//            if(second.charAt(second.length()-1) == 'b'){
//                int len = second.length();
//                int i=0;
//                int j=0;
//                for(;i<=(get(first).length - len);i++){
//                    secondTemp[i] = 0;
//                }
//                for(;i<get(first).length;i++){
//                    secondTemp[i] = Integer.parseInt(String.valueOf(second.charAt(j)));
//                    j++;
//                }
//            }else if(second.charAt(second.length()-1) == 'h'){
//                String j = Functions.HexToBin(second.substring(0, second.length()-1));
//                int i = 0;
//                int k = 0;
//                for(;i<get(first).length - (second.length()-1)*4;i++){
//                    secondTemp[i] = 0;
//                }
//                for(;i<get(first).length;i++){
//                    secondTemp[i] = Integer.parseInt(String.valueOf(j.charAt(k)));
//                    k++;
//                }
//            }else{ //decimal
//                String j;
//                if(second.charAt(second.length()-1) == 'd'){
//                    j = Functions.DecToBin(second.substring(0, second.length()-1));
//                }else{
//                    j = Functions.DecToBin(second);
//                }
//                int c=31;
//                for (int i = get(first).length -1;i>=0;i--){
//                    secondTemp[i] = Integer.parseInt(String.valueOf(j.charAt(c)));
//                    c--;
//                }
//            }
//        }else{
//            secondTemp = get(second);
//        }
        for(int i=0;i<get(first).length;i++){
            Temp[i] = firstTemp[i] * secondTemp[i];
        }
        set(first, Temp);
        setZNFlags(first);
        Main.Carry = 0;
        Main.Overflow = 0;
        Main.AuxiliaryCarry = 0;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    static void OR(String first, String second) {
        Integer[] Temp = new Integer[get(first).length];
        GetStandardValue(first, second);
//        Integer[] firstTemp = get(first);
//        Integer[] secondTemp = new Integer[get(first).length];
//        if(Character.isDigit(second.charAt(0))){ // 8 or 16 or 32 bits
//            if(second.charAt(second.length()-1) == 'b'){
//                int len = second.length();
//                int i=0;
//                int j=0;
//                for(;i<=(get(first).length - len);i++){
//                    secondTemp[i] = 0;
//                }
//                for(;i<get(first).length;i++){
//                    secondTemp[i] = Integer.parseInt(String.valueOf(second.charAt(j)));
//                    j++;
//                }
//            }else if(second.charAt(second.length()-1) == 'h'){
//                String j = Functions.HexToBin(second.substring(0, second.length()-1));
//                int i = 0;
//                int k = 0;
//                for(;i<get(first).length - (second.length()-1)*4;i++){
//                    secondTemp[i] = 0;
//                }
//                for(;i<get(first).length;i++){
//                    secondTemp[i] = Integer.parseInt(String.valueOf(j.charAt(k)));
//                    k++;
//                }
//            }else{ //decimal
//                String j;
//                if(second.charAt(second.length()-1) == 'd'){
//                    j = Functions.DecToBin(second.substring(0, second.length()-1));
//                }else{
//                    j = Functions.DecToBin(second);
//                }
//                int c=31;
//                for (int i = get(first).length -1;i>=0;i--){
//                    secondTemp[i] = Integer.parseInt(String.valueOf(j.charAt(c)));
//                    c--;
//                }
//            }
//        }else{
//            secondTemp = get(second);
//        }
        for(int i=0;i<get(first).length;i++){
            if((firstTemp[i] + secondTemp[i]) > 0)
                Temp[i] = 1;
            else
                Temp[i] = 0;
        }
        set(first, Temp);
        setZNFlags(first);
        Main.Carry = 0;
        Main.Overflow = 0;
        Main.AuxiliaryCarry = 0;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    static void ADD(String first, String second) {
        Integer[] Temp = new Integer[get(first).length];
        GetStandardValue(first, second);
        int car=0;
        int last_car=0;
        int temp;
        for(int i=get(first).length - 1;i>=0;i--){
            temp = firstTemp[i] + secondTemp[i] + car;
            if(temp == 0) {
                Temp[i] = 0;
                car = 0;
            }
            else if(temp == 1){
                Temp[i] = 1;
                car = 0;
            }
            else if(temp == 2){
                Temp[i] = 0;
                car = 1;
            }
            else if(temp == 3){
                Temp[i] = 1;
                car = 1;
            }
            if(get(first).length == 8 && i == 4) {
                if (car == 1)
                    Main.AuxiliaryCarry = 1;
                else
                    Main.AuxiliaryCarry = 0;
            }else if(get(first).length == 16 && i == 14) {
                if (car == 1)
                    Main.AuxiliaryCarry = 1;
                else
                    Main.AuxiliaryCarry = 0;
            }else if(get(first).length == 32 && i == 28) {
                if (car == 1)
                    Main.AuxiliaryCarry = 1;
                else
                    Main.AuxiliaryCarry = 0;
            }
            if(i==1)
                last_car = car;
        }
        if(car == 1)
            Main.Carry = 1;
        else
            Main.Carry = 0;


//        StringBuilder fTemp = new StringBuilder();
//        StringBuilder sTemp = new StringBuilder();
//
//        for (int i=0;i<firstTemp.length;i++){
//            fTemp.append(firstTemp[i]);
//        }
//        for (int i=0;i<secondTemp.length;i++){
//            sTemp.append(secondTemp[i]);
//        }
//        int b1 = Integer.parseInt(String.valueOf(fTemp), 2);
//        int b2 = Integer.parseInt(String.valueOf(sTemp), 2);
//        int sum = b1 + b2;
//        int i=0;
//        int k=0;
////        for(;i<get(first).length - Functions.DecToBin(String.valueOf(sum)).length();i++)
////            Temp[i] = 0;
////        for(;i<get(first).length;i++) {
////            Temp[i] = Integer.parseInt(String.valueOf(Functions.DecToBin(String.valueOf(sum)).charAt(k)));
////            k++;
////        }
//        for(;i<get(first).length - Integer.toBinaryString(sum).length();i++)
//            Temp[i] = 0;
//        for(;i<get(first).length;i++) {
//            Temp[i] = Integer.parseInt(String.valueOf(Integer.toBinaryString(sum).charAt(k)));
//            k++;
//        }
        set(first, Temp);
        setZNFlags(first);
        Main.Overflow = Main.Carry ^ last_car;
    }

    static void SUB(String first, String second) {
        Integer[] Temp = new Integer[get(first).length];
        GetStandardValue(first, second);
        secondTemp = Functions.TwosComp(secondTemp, get(first).length);
        int car=0;
        int last_car=0;
        int temp;
        for(int i=get(first).length - 1;i>=0;i--){
            temp = firstTemp[i] + secondTemp[i] + car;
            if(temp == 0) {
                Temp[i] = 0;
                car = 0;
            }
            else if(temp == 1){
                Temp[i] = 1;
                car = 0;
            }
            else if(temp == 2){
                Temp[i] = 0;
                car = 1;
            }
            else if(temp == 3){
                Temp[i] = 1;
                car = 1;
            }
            if(get(first).length == 8 && i == 4) {
                if (car == 1)
                    Main.AuxiliaryCarry = 1;
                else
                    Main.AuxiliaryCarry = 0;
            }else if(get(first).length == 16 && i == 14) {
                if (car == 1)
                    Main.AuxiliaryCarry = 1;
                else
                    Main.AuxiliaryCarry = 0;
            }else if(get(first).length == 32 && i == 28) {
                if (car == 1)
                    Main.AuxiliaryCarry = 1;
                else
                    Main.AuxiliaryCarry = 0;
            }
            if(i==1)
                last_car = car;
        }
        if(car == 1)
            Main.Carry = 1;
        else
            Main.Carry = 0;



//        StringBuilder fTemp = new StringBuilder();
//        StringBuilder sTemp = new StringBuilder();
//
//        for (int i=0;i<firstTemp.length;i++){
//            fTemp.append(firstTemp[i]);
//        }
//        for (int i=0;i<secondTemp.length;i++){
//            sTemp.append(secondTemp[i]);
//        }
//        int b1 = Integer.parseInt(String.valueOf(fTemp), 2);
//        int b2 = Integer.parseInt(String.valueOf(sTemp), 2);
//        int sum = b1 - b2;
//        int i=0;
//        int k=0;
//        for(;i<get(first).length - Integer.toBinaryString(sum).length();i++)
//            Temp[i] = 0;
//        for(;i<get(first).length;i++) {
//            Temp[i] = Integer.parseInt(String.valueOf(Integer.toBinaryString(sum).charAt(k)));
//            k++;
//        }
        set(first, Temp);
        setZNFlags(first);
        Main.Overflow = Main.Carry ^ last_car;
    }

    static void XCHG(String first, String second) {
        GetStandardValue(first, second);
        set(first, secondTemp);
        set(second, firstTemp);
    }

    static void TEST(String first, String second) {
        Integer[] Temp = new Integer[get(first).length];
        GetStandardValue(first, second);
        for(int i=0;i<get(first).length;i++){
            Temp[i] = firstTemp[i] * secondTemp[i];
        }
        setZNFlags(first);
        Main.Carry = 0;
        Main.Overflow = 0;
    }

    static void MOV(String first, String second) {
        GetStandardValue(first, second);
        set(first, secondTemp);
        setZNFlags(first);
        Main.Carry = 0;
        Main.Overflow = 0;
        Main.AuxiliaryCarry = 0;
    }

    static void XOR(String first, String second){
        Integer[] Temp = new Integer[get(first).length];
        GetStandardValue(first, second);
        for(int i=0;i<get(first).length;i++){
            Temp[i] = firstTemp[i] ^ secondTemp[i];
        }
        set(first, Temp);
        setZNFlags(first);
        Main.Carry = 0;
        Main.Overflow = 0;
        Main.AuxiliaryCarry = 0;
    }

    static void INC(String first){
        ADD(first, "1");
    }

    static void DEC(String first){
        SUB(first, "1");
    }

    static void STC(){
        Main.Carry = 1;
    }

    static void CLC(){
        Main.Carry = 0;
    }

    static void MOVZX(String first, String second){
        firstTemp = get(first);
        secondTemp = get(second);
        int i=0;
        for(;i<firstTemp.length-secondTemp.length;i++){
            firstTemp[i] = 0;
        }
        for(;i<firstTemp.length;i++){
            firstTemp[i] = secondTemp[i-(firstTemp.length-secondTemp.length)];
        }
        set(first, firstTemp);
        setZNFlags(first);
        Main.Carry = 0;
        Main.Overflow = 0;
        Main.AuxiliaryCarry = 0;
    }

    static void MOVSX(String first, String second){
        firstTemp = get(first);
        secondTemp = get(second);
        int i=0;
        for(;i<firstTemp.length-secondTemp.length;i++){
            firstTemp[i] = secondTemp[0];
        }
        for(;i<firstTemp.length;i++){
            firstTemp[i] = secondTemp[i-(firstTemp.length-secondTemp.length)];
        }
        set(first, firstTemp);
        setZNFlags(first);
        Main.Carry = 0;
        Main.Overflow = 0;
        Main.AuxiliaryCarry = 0;
    }
}
