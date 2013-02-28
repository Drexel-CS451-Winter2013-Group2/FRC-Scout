function getContentItems(){
    return [{
        title: 'Add User',
        xtype: 'form',
        width: 400,
        frame: true,
        bodyPadding: 5,
        url: 'insertUser.jsp',
        standardSubmit: true,
        fieldDefaults: {
            labelAlign: 'left',
            labelWidth: 90,
            anchor: '100%'
        },
        items: [{
            xtype: 'textfield',
            name: 'email',
            fieldLabel: 'Email:',
            vtype: 'email',
            allowBlank: false
        }, {
            xtype: 'textfield',
            inputType: 'password',
            name: 'password',
            fieldLabel: 'Password:',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'firstName',
            fieldLabel: 'First Name:'
        }, {
            xtype: 'textfield',
            name: 'lastName',
            fieldLabel: 'Last Name:'
        }, {  
            xtype: 'fieldcontainer',
            fieldLabel : 'Role',
            defaultType: 'radiofield',
            defaults: {
                padding: '0 10 0 0'
            },
            layout: 'hbox',
            items: [
                {
                    boxLabel: 'Admininstrator',
                    name: 'role',
                    inputValue: '3'
                }, {
                    boxLabel: 'Scout',
                    name: 'role',
                    inputValue: '2'
                }, {
                    boxLabel: 'Team Member',
                    name: 'role',
                    inputValue: '1',
                    checked: true
                }
            ]}
        ],
        buttons: [{
            text: 'Save',
            handler: function() {
                this.up('form').getForm().isValid();
                this.up('form').getForm().submit();
            }
        },{
            text: 'Cancel',
            handler: function() {
                window.location = "/frcscout/admin/admin.jsp?tab=0";
            }
        }]
    }];
}
