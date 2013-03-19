function getContentItems(){
    return [{
        title: 'Add Match Record',
        xtype: 'form',
        width: 340,
        frame: true,
        bodyPadding: 5,
        url: 'insertMatchRecord.jsp',
        standardSubmit: true,
        fieldDefaults: {
            labelAlign: 'left',
            labelWidth: 90,
            anchor: '100%'
        },
        items: [{
            xtype: 'combo',
            mode: 'local',
            triggerAction: 'all',
            forceSelection: true,
            editable: false,
            fieldLabel: 'Team Number:',
            name: 'teamId',
            displayField: 'id',
            valueField: 'id',
            queryMode: 'local',
            store: Ext.create('Ext.data.Store', {
                fields: ['id', 'name', 'location'],
                data: teamJSON
            }),
            allowBlank: false
        }, {
            xtype: 'button',
            text: 'Add Team',
            handler: function() {
                window.location = "/frcscout/scout/addTeam.jsp?return=scout/addMatchRecord.jsp";
            }
            
        }, {
            xtype: 'combo',
            mode: 'local',
            triggerAction: 'all',
            forceSelection: true,
            editable: false,
            fieldLabel: 'Event Name:',
            name: 'eventId',
            displayField: 'name',
            valueField: 'id',
            queryMode: 'local',
            store: Ext.create('Ext.data.Store', {
                fields: ['id', 'name', 'location', 'start_date', 'end_date'],
                data: eventJSON
            }),
            allowBlank: false
        }, {
            xtype: 'textfield', //TODO should be by event / match
            name: 'matchId',
            fieldLabel: 'Match Number:',
            regex: /^[0-9]+$/,
            regexText:'Match number must be an integer',
            allowBlank: false
        }, {
            xtype: 'radiogroup',
            fieldLabel: 'Color',
            items: [{
                boxLabel: 'Red', name: 'color', inputValue: 'red', checked: true
            }, {
                boxLabel: 'Blue', name: 'color', inputValue: 'blue'
            }]
        }, {
            xtype: 'numberfield', 
            name: 'autonTop',
            value: 0,
            minValue: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: 'Auton Top:',
        }, {
            xtype: 'numberfield', 
            name: 'autonMiddle',
            minValue: 0,
            value: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: 'Auton Middle:',
        }, {
            xtype: 'numberfield', 
            name: 'autonBottom',
            minValue: 0,
            value: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: 'Auton Bottom:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopTop',
            minValue: 0,
            value: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: 'Teleop Top:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopMiddle',
            minValue: 0,
            value: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: 'Teleop Middle:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopBottom',
            minValue: 0,
            value: 0,
            fieldLabel: 'Teleop Bottom:',
        },{
            xtype: 'numberfield', 
            name: 'teleopPyramid',
            minValue: 0,
            value: 0,
            allowBlank: false,
            allowDecimals: false,
            fieldLabel: 'Teleop Pyramid:',
        }, {
            xtype: 'radiogroup',
            fieldLabel: 'Pyramid Level',
            items: [{
                boxLabel: '0', name: 'pyramidLevel', inputValue: '0', checked: true
            }, {
                boxLabel: '1', name: 'pyramidLevel', inputValue: '1'
            }, {
                boxLabel: '2', name: 'pyramidLevel', inputValue: '2'
            }, {
                boxLabel: '3', name: 'pyramidLevel', inputValue: '3'
            }]
        },{
            xtype: 'radiogroup', 
            fieldLabel: 'Play Style',
            items: [{
                boxLabel: 'Offensive', name: 'playStyle', inputValue: 'Offensive', checked: true
            }, {
                boxLabel: 'Defensive', name: 'playStyle', inputValue: 'Defensive'
            }]
        },{
            xtype: 'numberfield', 
            name: 'confidence',
            fieldLabel: 'Rating (0 - 5):',
            value: 0,
            maxValue: 5,
            minValue: 0
        },{
            xtype: 'numberfield', 
            name: 'ability',
            fieldLabel: 'Floor Pickup Ability (0 - 5):',
            value: 0,
            maxValue: 5,
            minValue: 0
        },{
            xtype: 'checkbox', 
            name: 'fouls',
            fieldLabel: 'Fouls:',
        },{
            xtype: 'checkbox', 
            name: 'technicalFouls',
            fieldLabel: 'Technical Fouls:',
        },{
            xtype: 'textarea', 
            name: 'comments',
            fieldLabel: 'Comments:',
        }/*,{ //TODO
            xtype: 'textarea', 
            name: 'path',
            fieldLabel: 'Path:',
        }*/],
        buttons: [{
            text: 'Save',
            handler: function() {
                this.up('form').getForm().isValid();
                this.up('form').getForm().submit();
            }
        },{
            text: 'Cancel',
            handler: function() {
                window.location = "/frcscout/scout/scout.jsp";
            }
        }]
    }];
}
