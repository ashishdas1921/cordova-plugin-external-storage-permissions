var permissionsName = 'Permissions';

function ExternalStoragePermissions() {
    this.READ_EXTERNAL_STORAGE = 'android.permission.READ_EXTERNAL_STORAGE';
    this.WRITE_EXTERNAL_STORAGE = 'android.permission.WRITE_EXTERNAL_STORAGE';
}

ExternalStoragePermissions.prototype = {
    checkPermission: function(permission, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, permissionsName, 'checkPermission', [permission]);
    },
    requestPermission: function(permission, successCallback, errorCallback) {
        if (typeof permission === "function") {
            successCallback = arguments[0];
            errorCallback = arguments[1];
            permission = arguments[2];
        }
        cordova.exec(successCallback, errorCallback, permissionsName, 'requestPermission', [permission]);
    },
    requestPermissions: function(permissions, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, permissionsName, 'requestPermissions', permissions);
    }
};

module.exports = new ExternalStoragePermissions();