function getContentItems(){
    return [{
        title: 'Edit Team',
        xtype: 'form',
        width: 340,
        frame: true,
        bodyPadding: 5,
        url: 'updateTeam.jsp',
        standardSubmit: true,
        fieldDefaults: {
            labelAlign: 'left',
            labelWidth: 90,
            anchor: '100%'
        },
        items: [{
            xtype: 'hiddenfield',
            name: 'id',
            value: getURLParameter("id")
        }, {
            xtype: 'numberfield',
            name: 'number',
            value: getURLParameter("id"),
            fieldLabel: 'Team Number:',
            allowBlank: false,
            allowDecimals: false,
            minValue: 1,
            maxValue: 9999
        }, {
            xtype: 'textfield',
            name: 'name',
            value: name,
            fieldLabel: 'Name:',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'location',
            value: loc,
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
