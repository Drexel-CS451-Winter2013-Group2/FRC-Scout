function getContentItems(){
    return [{
        title: 'Add Match Record',
        xtype: 'form',
        width: 340,
        frame: true,
        bodyPadding: 5,
        url: 'updateMatchRecord.jsp',
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
        },{
            xtype: 'combo',
            mode: 'local',
            triggerAction: 'all',
            forceSelection: true,
            editable: false,
            fieldLabel: 'Team Number:',
            name: 'teamId',
            displayField: 'id',
            valueField: 'id',
            value: teamId,
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
            value: eventId,
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
            value: matchId,
            fieldLabel: 'Match Number:',
            regex: /^[0-9]+$/,
            regexText:'Match number must be an integer',
            allowBlank: false
        }, {
            xtype: 'radiogroup',
            fieldLabel: 'Color',
            items: [{
                boxLabel: 'Red', name: 'color', inputValue: 'red', checked: (color == 'red')
            }, {
                boxLabel: 'Blue', name: 'color', inputValue: 'blue', checked: (color == 'blue')
            }]
        }, {
            xtype: 'numberfield', 
            name: 'autonTop',
            value: autonTop,
            minValue: 0,
            fieldLabel: 'Auton Top:',
        }, {
            xtype: 'numberfield', 
            name: 'autonMiddle',
            minValue: 0,
            value: autonMiddle,
            fieldLabel: 'Auton Middle:',
        }, {
            xtype: 'numberfield', 
            name: 'autonBottom',
            minValue: 0,
            value: autonBottom,
            fieldLabel: 'Auton Bottom:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopTop',
            minValue: 0,
            value: teleopTop,
            fieldLabel: 'Teleop Top:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopMiddle',
            minValue: 0,
            value: teleopMiddle,
            fieldLabel: 'Teleop Middle:',
        }, {
            xtype: 'numberfield', 
            name: 'teleopBottom',
            minValue: 0,
            value: teleopBottom,
            fieldLabel: 'Teleop Bottom:',
        },{
            xtype: 'numberfield', 
            name: 'teleopPyramid',
            minValue: 0,
            value: teleopPyramid,
            fieldLabel: 'Teleop Pyramid:',
        }, {
            xtype: 'radiogroup',
            fieldLabel: 'Pyramid Level',
            items: [{
                boxLabel: '0', name: 'pyramidLevel', inputValue: '0', checked: (0 == pyramidLevel)
            }, {
                boxLabel: '1', name: 'pyramidLevel', inputValue: '1', checked: (1 == pyramidLevel)
            }, {
                boxLabel: '2', name: 'pyramidLevel', inputValue: '2', checked: (2 == pyramidLevel)
            }, {
                boxLabel: '3', name: 'pyramidLevel', inputValue: '3', checked: (3 == pyramidLevel)
            }]
        },{
            xtype: 'radiogroup', 
            fieldLabel: 'Play Style',
            items: [{
                boxLabel: 'Offensive', name: 'playStyle', inputValue: 'Offensive', checked: ('Offensive' == playStyle)
            }, {
                boxLabel: 'Defensive', name: 'playStyle', inputValue: 'Defensive', checked: ('Defensive' == playStyle)
            }]
        },{
            xtype: 'numberfield', 
            name: 'confidence',
            fieldLabel: 'Rating (0 - 10):',
            value: confidence,
            maxValue: 10,
            minValue: 0
        },{
            xtype: 'numberfield', 
            name: 'ability',
            fieldLabel: 'Floor Pickup Ability (0 - 10):',
            value: ability,
            maxValue: 10,
            minValue: 0
        },{
            xtype: 'checkbox', 
            name: 'fouls',
            checked: fouls,
            fieldLabel: 'Fouls:',
        },{
            xtype: 'checkbox', 
            name: 'technicalFouls',
            checked: technicalFouls,
            fieldLabel: 'Technical Fouls:',
        },{
            xtype: 'textarea', 
            name: 'comments',
            value: comments,
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
