package com.example.orb_slam2_android;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener{
	Button datasetMode,cameraMode;


	private String[] denied;
	private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_main);
        datasetMode=(Button)findViewById(R.id.dataset_mode);
        cameraMode=(Button)findViewById(R.id.camera_mode);
        datasetMode.setOnClickListener(this);
        cameraMode.setOnClickListener(this);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < permissions.length; i++) {
				if (PermissionChecker.checkSelfPermission(this, permissions[i]) == PackageManager.PERMISSION_DENIED) {
					list.add(permissions[i]);
				}
			}
			if (list.size() != 0) {
				denied = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					denied[i] = list.get(i);
					ActivityCompat.requestPermissions(this, denied, 5);
				}

			} else {
				init();
			}
		}

    }
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == 5) {
			boolean isDenied = false;
			for (int i = 0; i < denied.length; i++) {
				String permission = denied[i];
				for (int j = 0; j < permissions.length; j++) {
					if (permissions[j].equals(permission)) {
						if (grantResults[j] != PackageManager.PERMISSION_GRANTED) {
							isDenied = true;
							break;
						}
					}
				}
			}
			if (isDenied) {
				Toast.makeText(this, "请开启权限", Toast.LENGTH_SHORT).show();
			} else {
				init();

			}
		}
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.dataset_mode:
			startActivity(new Intent(MainActivity.this,DataSetModeActivity.class));
			break;
		case R.id.camera_mode:
			//Toast.makeText(MainActivity.this, "on the way...", Toast.LENGTH_LONG).show();
			startActivity(new Intent(MainActivity.this,ORBSLAMForCameraActivity.class));
			break;
		}
	}
	private void init(){

	}
}
