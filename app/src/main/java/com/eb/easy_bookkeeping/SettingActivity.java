package com.eb.easy_bookkeeping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.eb.easy_bookkeeping.db.DBManager;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_iv_back:
                finish();
                break;
            case R.id.setting_tv_clear:
                showDeleteDialog();
                break;
            case R.id.setting_tv_engel:
                showEngelCoefficientDialog();
                break;
            case R.id.setting_tv_change_typename:
                changeTypenameDialog();
                break;
        }
    }

    private void changeTypenameDialog() {
        final EditText inputServerSrc = new EditText(this);
        final EditText inputServerDes = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更改类别名称")
                .setMessage("被更改的类别名称为：")
                .setView(inputServerSrc)
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(DBManager.hasTypenameInTypetb(inputServerSrc.getText().toString())) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                            builder.setTitle("更改类别名称")
                                    .setMessage("要更改为：")
                                    .setView(inputServerDes)
                                    .setNegativeButton("取消", null)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            DBManager.updateTypenameFromTypetbByTypename(inputServerSrc.getText().toString(), inputServerDes.getText().toString());
                                            Toast.makeText(SettingActivity.this, "更改成功！", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            builder.create().show();
                        } else {
                            Toast.makeText(SettingActivity.this, "啊哦，名称不存在呢", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
        builder.create().show();
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除提示")
                .setMessage("您确定要删除所有记录么？\n注意：删除后无法恢复，请慎重选择！")
                .setPositiveButton("取消",null)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager.deleteAllAccount();
                        Toast.makeText(SettingActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }

    private void showEngelCoefficientDialog() {
        float engelCoefficient = DBManager.getEngelCoefficient();
        String hint;
        String str;
        if (engelCoefficient < 0.3) {
            hint = "最富裕！钱花不完可以多多打赏哦~";
        } else if (engelCoefficient < 0.4) {
            hint = "富裕~钱挣够了可以好好享受生活呀";
        } else if (engelCoefficient < 0.5) {
            hint = "小康，生活还是很滋润滴。";
        } else if (engelCoefficient < 0.59) {
            hint = "温饱，争取为全面建成小康社会贡献一份力量！";
        } else {
            hint = "贫困，脱贫攻坚战漏网之鱼嘛呜呜呜";
        }
        str = String.format("%.3f", engelCoefficient) + "\n" + hint;
        if(Float.isNaN(engelCoefficient)) {
            str = "咦？找不到支出记录呢，快去记一笔叭！";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("恩格尔系数为：")
                .setMessage(str)
                .setNegativeButton("确定", null);
        builder.create().show();
    }
}
