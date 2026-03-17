public class ArrayEstatico {
    
public static void main(String[] args) {
    

    int[] numeros;
    numeros = new int[8];

    int tamanho = 0;

    tamanho = inserirNoFim(numeros, tamanho, 10);
    tamanho = inserirNoFim(numeros, tamanho, 20);



    System.out.println("Capacidade do array " + numeros.length);
     System.out.println("Elemento no indice 1" + numeros[1]);
        System.out.println("Elemento no indice 1" + numeros);
    exibir(numeros, tamanho);
    }

    public static int inserirNoFim(int[] array, int tamanho, int valor ){
        if (tamanho >= array.length){
            System.out.println("Ta cheio" + valor) ;
            return tamanho;
        }

        array[tamanho] = valor;
        return tamanho + 1;
}


public static void exibir (int[] array, int tamanho) {
for (int i = 0; i < tamanho; i++){
    System.out.println("Indice" +  i  +" ->" + array[i]);
}
}
}
