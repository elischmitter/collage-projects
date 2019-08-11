//Wesley Grove, Eric chandler, Eli Schmitter

import java.util.Arrays;

public class MyArray<E>{
    private E[] array;

    public E get(int x)throws ArrayIndexOutOfBoundsException {
        return array[x];
    }

    public void set(int x,E y) throws ArrayIndexOutOfBoundsException {
        if(x>=array.length){
            E[] temp = (E[]) new Object[x + 1];

            for (int i=0;i<array.length;i++){
                temp[i]=array[i];
            }
            temp[x]=y;
            array=temp;
        }
        else{
            array[x]=y;
        }

    }

    @Override
    public String toString() {
        return "["+Arrays.toString(array)+"]";
    }

    public boolean equals(MyArray<E> o) {
    try {
        for (int i = 0; i < array.length; i++) {
            if (!o.get(i).equals(array[i])) {
                return false;
            }
        }
    } catch (Exception e) {
        return false;
    }
    return true;
    }


    MyArray(int x){
        array= (E[]) new Object[x];
    }
    MyArray(){
        array= (E[]) new Object[0];
    }
}
