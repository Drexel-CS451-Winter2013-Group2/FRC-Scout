function getContentItems(){
    return [{
        title: 'Edit Event',
        xtype: 'form',
        width: 340,
        frame: true,
        bodyPadding: 5,
        url: 'updateEvent.jsp',
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
        }, {
            xtype: 'datefield',
            name: 'startDate',
            value: startDate,
            fieldLabel: 'Start Date:',
            allowBlank: false
        }, {
            xtype: 'datefield',
            name: 'endDate',
            value: endDate,
            fieldLabel: 'End Date:',
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
                window.location = "/frcscout/admin/admin.jsp?tab=2";
            }
        }]
    }];
}
