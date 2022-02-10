var permissionsName = 'Permissions';

function ExternalStoragePermissions() {
    this.READ_EXTERNAL_STORAGE = 'android.permission.READ_EXTERNAL_STORAGE';
    this.WRITE_EXTERNAL_STORAGE = 'android.permission.WRITE_EXTERNAL_STORAGE';
}

ExternalStoragePermissions.prototype = {
    checkPermission: function(permission, successCallback, errorCallback) {
        successCallback( {hasPermission: true} );
    },
    requestPermission: function(permission, successCallback, errorCallback) {
        if (typeof permission === "function") {
            successCallback = arguments[0];
            errorCallback = arguments[1];
            permission = arguments[2];
        }
        successCallback( {hasPermission: true} );
    },
    requestPermissions: function(permissions, successCallback, errorCallback) {
        successCallback( {hasPermission: true} );
    }
};

module.exports = new ExternalStoragePermissions();