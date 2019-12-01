package domain.com.study.sqlite;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import domain.com.study.sqlite.base.BaseActivity;
import domain.com.study.sqlite.db.DbUserManager;
import domain.com.study.sqlite.entity.User;

public class MainActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_condition)
    EditText etCondition;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_del)
    Button btnDel;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.tv_show)
    TextView tvShow;

    String name;
    String age;
    String condition;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.btn_add, R.id.btn_del, R.id.btn_update, R.id.btn_query})
    public void onViewClicked(View view) {
        getValue();
        switch (view.getId()) {
            case R.id.btn_add:
                DbUserManager.getInstance(getApplicationContext()).insertUser(new User(name,Integer.valueOf(age)));
                break;
            case R.id.btn_del:
                DbUserManager.getInstance(getApplicationContext()).delByName(condition);
                break;
            case R.id.btn_update:
                DbUserManager.getInstance(getApplicationContext()).updateByName(new User(name,Integer.valueOf(age)),condition);
                break;
            case R.id.btn_query:
                List<User> users = DbUserManager.getInstance(getApplicationContext()).queryByName(condition);
                tvShow.setText(users.toString());
                break;
        }
    }

    private void  getValue(){
        name = etName.getText().toString();
        age = etAge.getText().toString();
        condition=etCondition.getText().toString();
    }
}
