package ru.kolesnikovdmitry.lesson10popupmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewActMain;
    Button   mButtonActMain;
    int      colorBtnActMain      = R.color.colorBtnOrange;
    int      colorTextViewActMain = R.color.colorTextBlack;
    boolean  isTextCaps       = false;

    final int   subMenuItemTextColorId  = 102;
    final int   menuItemTextCapsId      = 103;

    private PopupMenu.OnMenuItemClickListener onMenuItemTextColorClick = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.color.colorTextBlack:
                    item.setCheckable(!item.isChecked());
                    colorTextViewActMain = R.color.colorTextBlack;
                    mTextViewActMain.setTextColor(getColor(colorTextViewActMain));
                    break;
                case R.color.colorTextBlue:
                    item.setCheckable(!item.isChecked());
                    colorTextViewActMain = R.color.colorTextBlue;
                    mTextViewActMain.setTextColor(getColor(colorTextViewActMain));
                    break;
                case R.color.colorTextPink:
                    item.setCheckable(!item.isChecked());
                    colorTextViewActMain = R.color.colorTextPink;
                    mTextViewActMain.setTextColor(getColor(colorTextViewActMain));
                    break;
                case menuItemTextCapsId:
                    item.setCheckable(!item.isChecked());
                    isTextCaps = !isTextCaps;
                    mTextViewActMain.setAllCaps(isTextCaps);
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    //обрабатываем долгое нажатие на текст
    View.OnLongClickListener onLongClickTextActMain = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            showMenuTextColor(v);
            return false;
        }
    };

    //долгое нажатие на кнопку
    View.OnLongClickListener onLongClickBtnActMain = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            showMenuBtnColor(v);
            return true;
        }
    };

    //функция срабатывает при закрытии какого либо меню(можно для каждого отдельную)
    PopupMenu.OnDismissListener onDismissPopupMenuActMain = new PopupMenu.OnDismissListener() {
        @Override
        public void onDismiss(PopupMenu menu) {
            //TODO: add snackbar.
        }
    };

    //слушатель нажатия айтемов попап меню выбора цвета кнопки
    PopupMenu.OnMenuItemClickListener onMenuItemBtnColorClick = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menuItemBtnColorGreenMain:
                    item.setChecked(!item.isCheckable()); //меняем показатель выбрано/нет
                    colorBtnActMain = R.color.colorBtnGreen;  //меняем переменную цвета кнопки
                    mButtonActMain.setBackground(getDrawable(colorBtnActMain)); //устанавливаем новый цвет кнопки.
                    break;
                case R.id.menuItemBtnColorRedMain:
                    item.setChecked(!item.isCheckable());
                    colorBtnActMain = R.color.colorBtnRed;
                    mButtonActMain.setBackground(getDrawable(colorBtnActMain));
                    break;
                case R.id.menuItemBtnColorYellowMain:
                    item.setChecked(!item.isCheckable());
                    colorBtnActMain = R.color.colorBtnOrange;
                    mButtonActMain.setBackground(getDrawable(colorBtnActMain));
                    break;
                default:
                    break;
            }
            return true;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewActMain = findViewById(R.id.textViewActMain);
        mButtonActMain   = findViewById(R.id.btnTxtColorActMain);

        mButtonActMain.setBackground(getDrawable(colorBtnActMain));
        mTextViewActMain.setTextColor(getColor(colorTextViewActMain));

        mButtonActMain.setOnLongClickListener(onLongClickBtnActMain);
        mTextViewActMain.setOnLongClickListener(onLongClickTextActMain);

        mTextViewActMain.setAllCaps(isTextCaps);
    }






    //показываем попап меню выбора цвета текста
    private void showMenuTextColor(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        //программно добавляем подменюшки в попап меню
        popupMenu.getMenu().add(subMenuItemTextColorId, R.color.colorTextPink,  1, "Pink");
        popupMenu.getMenu().add(subMenuItemTextColorId, R.color.colorTextBlue,  2, "Blue");
        popupMenu.getMenu().add(subMenuItemTextColorId, R.color.colorTextBlack, 3, "Black");
        popupMenu.getMenu().setGroupCheckable(subMenuItemTextColorId, true, true);
        popupMenu.getMenu().add(menuItemTextCapsId, menuItemTextCapsId, 1, "Заглавные").setCheckable(true).setChecked(isTextCaps);
        popupMenu.setOnMenuItemClickListener(onMenuItemTextColorClick);

        switch (colorTextViewActMain) {
            case R.color.colorTextBlack:
                popupMenu.getMenu().findItem(R.color.colorTextBlack).setChecked(true);
                break;
            case R.color.colorTextBlue:
                popupMenu.getMenu().findItem(R.color.colorTextBlue).setChecked(true);
                break;
            case R.color.colorTextPink:
                popupMenu.getMenu().findItem(R.color.colorTextPink).setChecked(true);
                break;
            default:
                break;
        }
        popupMenu.show();
    }





    //показываем попап меню для выбора цвета кнопки
    private void showMenuBtnColor(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);    // Инициализируем меню, передавая два параметра: контекст и View
        popupMenu.inflate(R.menu.popup_menu_btn_color_main);                // насаживаем на этот элемент меню вьюшку (можно и программно)

        popupMenu.setOnMenuItemClickListener(onMenuItemBtnColorClick);      // сажаем клик листенер(он ниже)
        popupMenu.setOnDismissListener(onDismissPopupMenuActMain);          // это слушатель закрытия меню, мб пригодится

        popupMenu.getMenu().add(R.id.menuGroupTextColorMain, 1, Menu.NONE, "LOL");  //так можно добавлять новый элемент меню программно

        try{
            switch (colorBtnActMain) {
                case R.color.colorBtnGreen:
                    popupMenu.getMenu().findItem(R.id.menuItemBtnColorGreenMain).setChecked(true); //если зеленая кнопка, то при создании меню мы сразу сделаем вбранную позицию зеленый, чтобы был вид выбора
                    break;
                case R.color.colorBtnRed:
                    popupMenu.getMenu().findItem(R.id.menuItemBtnColorRedMain).setChecked(true);
                    break;
                case R.color.colorBtnOrange:
                    popupMenu.getMenu().findItem(R.id.menuItemBtnColorYellowMain).setChecked(true);
                    break;
                default:
                    break;
            }
        }catch (Throwable th) {
            Toast.makeText(getApplicationContext(), th.getMessage(), Toast.LENGTH_LONG).show();
        }

        popupMenu.show(); //показываем меню
    }



    //кнопки с MainActivity
    public void onClickActivityMain(View view) {
        switch (view.getId()) {
            case R.id.btnTxtColorActMain:
                //TODO:...
                break;
            default:
                break;
        }
    }


}
