

Ext.define('ExcelApp.view.MyForm', {
    extend: 'Ext.form.Panel',
    alias : 'widget.fileuploadform',
    width: 500,
    frame: true,
    title: 'Order File Upload Form',
    bodyPadding: '10 10 0',
    renderTo: 'fileUpload',
    
    defaults: {
        anchor: '100%',
        allowBlank: false,
        msgTarget: 'side',
        labelWidth: 75
    },
 
    items: [{
        xtype: 'textfield',
        name: 'filename',
        fieldLabel: 'File Name'
    },{
        xtype: 'filefield',
        id: 'myFile',
        emptyText: 'Select a File to Upload',
        fieldLabel: 'Select a File',
        name: 'fileSelected',
        buttonText: '',
        buttonConfig: {
            iconCls: 'upload-icon'
        }
    }],
 
    buttons: [{
        text: 'Upload',
        action: 'uploadFile'
    },
    {
        text: 'Reset Form',
        handler: function() {
            this.up('form').getForm().reset();
        }
    }]
});
 
