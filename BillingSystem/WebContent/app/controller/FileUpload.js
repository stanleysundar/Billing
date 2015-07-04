

Ext.define('ExcelApp.controller.FileUpload', {
            extend : 'Ext.app.Controller',
 
            //define the views
            views : ['MyForm'],
 
            refs : [{
                ref : 'myForm',
                selector : 'fileuploadform'
            }],
            
            //special method that is called when your application boots
            init : function() {
                
                //control function makes it easy to listen to events on 
                //your view classes and take some action with a handler function
                this.control({
                    
                            //when the viewport is rendered
                            'viewport > panel' : {
                                render : this.onPanelRendered
                            },
                            //when you click Upload file button
                            'fileuploadform button[action=uploadFile]' : {
                                click : this.uploadFile   
                            }
                    });
            },
 
            onPanelRendered : function() {
                //just a console log to show when the panel is rendered
                console.log('The panel was rendered');
            },
            
            uploadFile : function(button) {
                //just a console log to show when the file Upload starts
                console.log('File Upload in progress');
                var form = button.up('form').getForm();
                
                if(form.isValid()){
                    form.submit({
                        url: 'ExcelOrderFileUpload',
                        waitMsg: 'Uploading your file...',
                        scope: this,
                        success: function(form, action){
                            // server responded with success = true
                            response = Ext.decode(action.response.responseText);
                            if(response.success){
                                this.processResponse(response);
                                form.reset();
                            }
                        },
                        failure: function(form, action){
                            if (action.failureType === CONNECT_FAILURE) {
                                   Ext.Msg.alert('Error', 'Status:'+action.response.status+': '+
                                action.response.statusText);
                            }
                            if (action.failureType === SERVER_INVALID){
                                // server responded with success = false
                                Ext.Msg.alert('Invalid', action.result.errormsg);
                            }
                        }
                    });
                }
            },   
            
            processResponse : function(response){
                
                responseMessage = response.message;
                Ext.MessageBox.show({
                    closable: false,
                    width:450,
                    title:'<b>Excel Order File Upload Successful!</b>',
                    msg: responseMessage,
                    buttons: Ext.MessageBox.OK,
                    fn: this.showResult,
                    icon: Ext.MessageBox.INFO
                });
            },
            
            showResult : function(btn){
                console.log('You clicked the ' + btn + ' button');
            }
        
    });
 
