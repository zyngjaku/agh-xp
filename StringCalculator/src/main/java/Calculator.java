public class Calculator {
    public int add(String numbers) throws Exception {
        String[] tab = new String[0];
        String splitter = ",|\n";
        if(numbers.length()>3&&numbers.substring(0,2).equals("//")){
            splitter = numbers.substring(2,numbers.indexOf('\n'));
            String toSplit = numbers.substring(numbers.indexOf('\n')+1);
            return add(toSplit,splitter);
        }
        else {
            tab = numbers.split(splitter);
        }
        if(tab.length == 0){
            return 0;
        }
        if (tab[0].equals("")) {
            return 0;
        }
        if(tab.length==1){
            return parseOne(tab[0]);
        }
        if(tab.length==2){
            return add(tab[0])+ add(tab[1]);
        }
        return add(tab[0])+ add(numbers.substring(tab[0].length()+1));
    }
    public int add(String numbers, String splitter) throws Exception {
        String[] tab = numbers.split(splitter);
        if(tab.length == 0){
            return 0;
        }
        if (tab[0].equals("")) {
            return 0;
        }
        if(tab.length==1){
            return parseOne(tab[0]);
        }
        if(tab.length==2){
            return add(tab[0])+ add(tab[1]);
        }
        return add(tab[0])+ add(numbers.substring(tab[0].length()+1),splitter);
    }
    private int parseOne(String number) throws Exception {
        int toReturn = Integer.parseInt(number);
        if(toReturn<0) throw new Exception("negatives not allowed: " + toReturn);
        return toReturn;
    }
}
