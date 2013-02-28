function getContentItems(){
    return [{
        title: 'Add Team',
        xtype: 'form',
        width: 340,
        frame: true,
        bodyPadding: 5,
        url: 'insertTeam.jsp',
        standardSubmit: true,
        fieldDefaults: {
            labelAlign: 'left',
            labelWidth: 90,
            anchor: '100%'
        },
        items: [{
            xtype: 'textfield',
            name: 'id',
            fieldLabel: 'Team Number:',
            regex: /^[0-9]+$/,
            regexText:'Team number must be an integer'
        }, {
            xtype: 'textfield',
            name: 'name',
            fieldLabel: 'Name:',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'location',
            fieldLabel: 'Location:'
        }],
        buttons: [{
            text: 'Save',
            handler: function() {
                this.up('form').getForm().isValid();
                this.up('form').getForm().submit();
            }
        },{
            text: 'Cancel',
            handler: function() {
                window.location = "/frcscout/admin/admin.jsp?tab=1";
            }
        }]
    }];
}
