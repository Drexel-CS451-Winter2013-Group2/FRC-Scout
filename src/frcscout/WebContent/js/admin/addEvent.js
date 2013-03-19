function getContentItems(){
    return [{
        title: 'Add Event',
        xtype: 'form',
        width: 340,
        frame: true,
        bodyPadding: 5,
        url: 'insertEvent.jsp',
        standardSubmit: true,
        fieldDefaults: {
            labelAlign: 'left',
            labelWidth: 90,
            anchor: '100%'
        },
        items: [{
            xtype: 'textfield',
            name: 'name',
            fieldLabel: 'Name:',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'location',
            fieldLabel: 'Location:',
            allowBlank: false
        }, {
            xtype: 'datefield',
            name: 'startDate',
            fieldLabel: 'Start Date:',
            allowBlank: false
        }, {
            xtype: 'datefield',
            name: 'endDate',
            fieldLabel: 'End Date:',
            allowBlank: false
        }, {
            xtype: 'combo',
            mode: 'local',
            value: 'ascent',
            triggerAction: 'all',
            forceSelection: true,
            editable: false,
            fieldLabel: 'Game:',
            name: 'game',
            displayField: 'name',
            valueField: 'value',
            queryMode: 'local',
            store: Ext.create('Ext.data.Store', {
                fields : ['name', 'value'],
                data   : [
                    {name : 'Ultimate Ascent',   value: 'ascent'}
                ]
            })
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
