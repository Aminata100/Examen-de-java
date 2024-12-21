package newstart.view;

import java.util.List;

import newstart.core.View;


public class ViewImpl<T> implements View<T>{
    @Override
    public void affiche(List<T> datas) {
        if (datas.isEmpty()) {
            System.out.println("Rien trouvé.");
        } else {
            for (T data : datas) {
                System.out.println(data);
            }
        }
    }

    @Override
    public T saisie() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saisie'");
    }
}
