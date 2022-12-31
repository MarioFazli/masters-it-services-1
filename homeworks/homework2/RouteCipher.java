public class RouteCipher {

    private int key;

    public RouteCipher(int key) {
        setKey(key);
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    private char[][] buildCipherMatrix(char[] cipherTextList){
        int colNumber = Math.abs(getKey());
        int rowNumber = (int) Math.ceil(cipherTextList.length / (colNumber * 1.0));
        char[][] encryptionMatrix = new char[rowNumber][colNumber];

        if (rowNumber * colNumber > cipherTextList.length) { //if the cipher size is less than the matrix => fill it with 'X'
            int difference = rowNumber * colNumber - cipherTextList.length;
            for (int i = 0; i < difference; i++) {
                encryptionMatrix[rowNumber - 1][colNumber - 1 - i] = 'X';
            }
        }
        int index = 0;
        for (int k = 0; k < rowNumber; k++) {
            for (int j = 0; j < colNumber; j++) {
                if(index == cipherTextList.length){
                    break;
                }
                encryptionMatrix[k][j] = cipherTextList[index++];
            }
        }
        return encryptionMatrix;
    }

    private char[] keepOnlyLetters(String text)
    {
        final int lengthOfPlainText = text.length();
        // method that returns a char[] with all of the inputted string
        // elements that are letters (without any unnecessary symbols)
        char[] textArr = text.toCharArray();    // converting the String into char[]
        char[] newArr = new char[textArr.length]; // making a copy of the array which will hold the elements without unnecessary symbols like , or spaces
        int counter = 0;                            // acts as a index helper
        for (char elem : textArr) {
            if (elem >= 'a' && elem <= 'z' || elem >= 'A' && elem <= 'Z') {
                newArr[counter++] = elem;
            }
        }
        textArr = new char[counter];
        for (int i = 0; i < counter; i++) {
            textArr[i] = newArr[i];
        }
        return textArr;
    }// end of returnUsableArr()


    public String encrypt(String plainText) {

        char[] cipherTextList = keepOnlyLetters(plainText);
        char[][] encryptionMatrix = buildCipherMatrix(cipherTextList);
        int colNumber = Math.abs(getKey());
        int rowNumber = (int) Math.ceil(cipherTextList.length / (colNumber * 1.0));

        int positionInMatrix = 0;
        int positionInEncryptedArr = 0;
        char[] encryptedArr = new char[rowNumber*colNumber];
        if (getKey() > 0) {
            int rowStart = 0;
            int colStart = 0;
            int rowEnd = rowNumber - 1;
            int colEnd = colNumber - 1;

            while(rowStart <= rowEnd && colStart <= colEnd){
                //go down
                for(positionInMatrix = rowStart; positionInMatrix <= rowEnd; positionInMatrix++){
                    encryptedArr[positionInEncryptedArr++] = encryptionMatrix[positionInMatrix][colStart];
                }
                // go right
                for(positionInMatrix = colStart+1; positionInMatrix <= colEnd; positionInMatrix++){
                    encryptedArr[positionInEncryptedArr++] = encryptionMatrix[rowEnd][positionInMatrix];
                }
                if(colStart < colEnd){
                    // go up
                    for(positionInMatrix = rowEnd-1; positionInMatrix >= rowStart; positionInMatrix--){
                        encryptedArr[positionInEncryptedArr++] = encryptionMatrix[positionInMatrix][colEnd];
                    }
                }

                if(rowStart < rowEnd){
                    // go left
                    for(positionInMatrix = colEnd-1; positionInMatrix > colStart; positionInMatrix--){
                        encryptedArr[positionInEncryptedArr++] = encryptionMatrix[rowStart][positionInMatrix];
                    }
                }

                rowStart++;
                colStart++;
                rowEnd--;
                colEnd--;
            }
        }
        else {
            // if key < 0 we start from the lowest right corner of the rectangle matrix

            int rowStart = rowNumber - 1, colStart = colNumber - 1, rowEnd = 0, colEnd = 0; // variables indicating the start and end


            while (rowStart >= rowEnd && colStart >= colEnd) {
                // going up
                for (positionInMatrix = rowStart; positionInMatrix >= rowEnd; positionInMatrix--) {
                    encryptedArr[positionInEncryptedArr++] = encryptionMatrix[positionInMatrix][colStart];
                }

                // going left
                for (positionInMatrix = colStart - 1; positionInMatrix >= colEnd; positionInMatrix--) {
                    encryptedArr[positionInEncryptedArr++] = encryptionMatrix[rowEnd][positionInMatrix];
                }

                if (colEnd < colStart) {
                    // going down
                    for (positionInMatrix = rowEnd + 1; positionInMatrix <= rowStart; positionInMatrix++) {
                        encryptedArr[positionInEncryptedArr++] = encryptionMatrix[positionInMatrix][colEnd];
                    }
                }

                if (rowEnd < colStart) {
                    // going right
                    for (positionInMatrix = colEnd + 1; positionInMatrix <= colStart - 1; positionInMatrix++) {
                        encryptedArr[positionInEncryptedArr++] = encryptionMatrix[rowStart][positionInMatrix];
                    }
                }

                rowStart--;
                colStart--;
                rowEnd++;
                colEnd++;
            }
        }
        return new String(encryptedArr).toUpperCase();
    }

    public String decrypt(String encryptedText) {
        char[] plaintextArr = keepOnlyLetters(encryptedText);
        char[][] encryptionMatrix = buildCipherMatrix(plaintextArr);
        int colNumber = Math.abs(getKey());
        int rowNumber = (int) Math.ceil(plaintextArr.length / (colNumber * 1.0));// Math.ceil(double a) -> *1.0
        int textIndex = 0;
                                /*the idea of decryption is to enter the symbols from the encrypted string the same way
                                 it was encrypted. For that we need the key */
        if (getKey() > 0) {
            // if key > 0 the program should start entering symbols from the upper left corner
            int rowStart=0,colStart=0,rowEnd=rowNumber-1,colEnd=colNumber-1; // variables indicating the start and end
            int i;

            // essentially saying that we should keep looping until the smaller variable (rows or absolute value) reaches
            // half of it's value -> rows/2 or absoluteValue/2 (if the number is odd it will be (rows/2)+1)
            while(rowStart<=rowEnd && colStart<= colEnd)
            {
                // going down a column
                for(i=rowStart;i<=rowEnd;i++)
                {
                    encryptionMatrix[i][colStart] = plaintextArr[textIndex++];
                }



                // going horizontally right
                for(i=colStart+1 ;i<=colEnd;i++)
                {
                    encryptionMatrix[rowEnd][i] = plaintextArr[textIndex++] ;
                }

                // if it is the final cycle and if there are no more columns it should not go in the loop
                if(colStart < colEnd)
                {
                    // going up a column
                    for(i=rowEnd-1;i>=rowStart;i--)
                    {
                        encryptionMatrix[i][colEnd] =  plaintextArr[textIndex++];
                    }

                }
                // if it is the final cycle and if there are no more rows to read it should not go in the loop
                if(rowStart < rowEnd)
                {
                    // going horizontally left
                    for(i=colEnd-1;i>colStart;i--)
                    {
                        encryptionMatrix[rowStart][i] = plaintextArr[textIndex++];
                    }

                }

                rowEnd--;
                colEnd--;
                colStart++;
                rowStart++;
            } // end of while loop

        } else if (getKey() < 0) {
            // if key < 0 the program should start entering symbols from the lower right corner

            // this is essentially the same as the above but reversed - since we start from the lower right corner
            // rowStart starts from there and ends in the beginning so they are reversed
            int rowStart= rowNumber-1,colStart= colNumber-1,rowEnd= 0,colEnd= 0; // variables indicating the start and end
            int i;

            while(rowStart>=rowEnd && colStart>= colEnd)
            {
                // going up starting from lower right corner
                for(i=rowStart;i>=rowEnd;i--)
                {
                    encryptionMatrix[i][colStart] = plaintextArr[textIndex++];
                }

                // going left
                for(i=colStart-1;i>= colEnd;i--)
                {
                    encryptionMatrix[rowEnd][i] = plaintextArr[textIndex++];
                }
                // if it is the final cycle and if there are no more columns it should not go in the loop
                if(colEnd < colStart)
                {
                    // going down
                    for(i=rowEnd + 1;i<= rowStart;i++)
                    {
                        encryptionMatrix[i][colEnd] = plaintextArr[textIndex++];
                    }
                }
                // if it is the final cycle and if there are no more rows to read it should not go in the loop
                if(rowEnd < colStart)
                {
                    // going right
                    for(i=colEnd + 1;i<= colStart -1;i++)
                    {
                        encryptionMatrix[rowStart][i] = plaintextArr[textIndex++];
                    }
                }

                rowStart--;
                colStart--;
                rowEnd++;
                colEnd++;
            } // end of while loop
        }

        int countX = 0;
        String decrypted = "";
        for (int i = 0; i < rowNumber; i++) {
            for (int n = 0; n < colNumber - countX; n++) {
                if(i == rowNumber-1)
                {
                    while(encryptionMatrix[i][colNumber-1-countX] == 'X')
                    {
                        countX++;
                    }
                }
                decrypted += encryptionMatrix[i][n];
            }
        }
        return decrypted;
    }
}
