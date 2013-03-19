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
            xtype: 'hidden',
            name: 'return',
            value: getURLParameter('return')
        }, {
            xtype: 'numberfield',
            name: 'id',
            fieldLabel: 'Team Number:',
            allowBlank: false,
            allowDecimals: false,
            minValue: 1,
            maxValue: 9999,
        }, {
            xtype: 'textfield',
            name: 'name',
            fieldLabel: 'Name:',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'location',
            fieldLabel: 'Location:',
            allowBlank: false
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
