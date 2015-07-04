Ext.Loader.setConfig({ 
    enabled: true
    });
 
Ext.application({
    name: 'ExcelApp',
 
    appFolder: 'app',
    
    controllers: [
                  'FileUpload'
              ],
    
    launch: function() {
        Ext.widget('fileuploadform');
    }
});
