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
            })
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
            })
        }, {
            xtype: 'textfield', //TODO should be by event / match
            name: 'matchId',
            fieldLabel: 'Match Number:',
            regex: /^[0-9]+$/,
            regexText:'Match number must be an integer'
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
            fieldLabel: 'Auton Top:',
        }, {
            xtype: 'numberfield', 
            name: 'autonMiddle',
            value: 0,
            fieldLabel: 'Auton Middle:',
        }, {
            xtype: 'numberfield', 
            name: 'autonBottom',
            value: 0,
            fieldLabel: 'Auton Bottom:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopTop',
            value: 0,
            fieldLabel: 'Teleop Top:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopMiddle',
            value: 0,
            fieldLabel: 'Teleop Middle:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopBottom',
            value: 0,
            fieldLabel: 'Teleop Bottom:',
        },{
            xtype: 'numberfield', 
            name: 'teleopPyramid',
            value: 0,
            fieldLabel: 'Teleop Pyramid:',
        }, {
            xtype: 'radiogroup',
            fieldLabel: 'Pyramid Level',
            items: [{
                boxLabel: '1', name: 'pyramidLevel', inputValue: '0', checked: true
            }, {
                boxLabel: '2', name: 'pyramidLevel', inputValue: '1'
            }, {
                boxLabel: '3', name: 'pyramidLevel', inputValue: '2'
            }, {
                boxLabel: '4', name: 'pyramidLevel', inputValue: '3'
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
            fieldLabel: 'Confidence (0 - 10):',
            value: 0,
            maxValue: 10,
            minValue: 0
        },{
            xtype: 'numberfield', 
            name: 'ability',
            fieldLabel: 'Ability (0 - 10):',
            value: 0,
            maxValue: 10,
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
