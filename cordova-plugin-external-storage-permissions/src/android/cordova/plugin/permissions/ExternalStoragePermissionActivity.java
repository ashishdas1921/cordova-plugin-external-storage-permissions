package cordova.plugin.permissions;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;

import org.json.JSONObject;

public class ExternalStoragePermissionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));
        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            JSONObject returnObj = new JSONObject();
            ExternalStoragePermissions.addProperty(returnObj, ExternalStoragePermissions.KEY_ERROR, "MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
            ExternalStoragePermissions.addProperty(returnObj, ExternalStoragePermissions.KEY_MESSAGE, "Unknown error.");
            ExternalStoragePermissions.notifyPermissionsCallback(false, returnObj);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2296) {
            JSONObject returnObj = new JSONObject();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ExternalStoragePermissions.addProperty(returnObj, ExternalStoragePermissions.KEY_RESULT_PERMISSION, Environment.isExternalStorageManager());
                ExternalStoragePermissions.notifyPermissionsCallback(true, returnObj);
            } else {
                ExternalStoragePermissions.addProperty(returnObj, ExternalStoragePermissions.KEY_ERROR, "MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
                ExternalStoragePermissions.addProperty(returnObj, ExternalStoragePermissions.KEY_MESSAGE, "Unknown error.");
                ExternalStoragePermissions.notifyPermissionsCallback(false, returnObj);
            }
        }
        finish();
    }
}