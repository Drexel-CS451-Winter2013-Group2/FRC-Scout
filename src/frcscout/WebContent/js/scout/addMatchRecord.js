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
        	  xtype: 'numberfield', // TODO list of teams?
              name: 'teamId',
              fieldLabel: 'Team Number:',
        }, {
        	  xtype: 'numberfield', //TODO should be by event / match
              name: 'matchId',
              fieldLabel: 'Match Id:',
        }, {
            xtype: 'textfield', //TODO radio button?
            name: 'color',
            fieldLabel: 'Color:'
        }, {
      	  xtype: 'numberfield', 
          name: 'autonTop',
          fieldLabel: 'Auton Top:',
    }, {
      	  xtype: 'numberfield', 
          name: 'autonMiddle',
          fieldLabel: 'Auton Middle:',
    }, {
      	  xtype: 'numberfield', 
          name: 'autonBottom',
          fieldLabel: 'Auton Bottom:',
    }, {
      	  xtype: 'numberfield', 
          name: 'teleopTop',
          fieldLabel: 'Teleop Top:',
    }, {
      	  xtype: 'numberfield', 
          name: 'teleopMiddle',
          fieldLabel: 'Teleop Middle:',
    }, {
      	  xtype: 'numberfield', 
          name: 'teleopBottom',
          fieldLabel: 'Teleop Bottom:',
    },{
      	  xtype: 'numberfield', 
          name: 'teleopPyramid',
          fieldLabel: 'Teleop Pyramid:',
    }, {
      	  xtype: 'numberfield', 
          name: 'pyramidLevel',
          fieldLabel: 'Pyramid Level:',
    },{
      	  xtype: 'textfield', 
          name: 'playStyle',
          fieldLabel: 'Play Style:',
    },{
      	  xtype: 'numberfield', 
          name: 'confidence',
          fieldLabel: 'Confidence:',
    },{
      	  xtype: 'numberfield', 
          name: 'ability',
          fieldLabel: 'Ability:',
    },{
      	  xtype: 'textfield', 
          name: 'fouls',
          fieldLabel: 'Fouls:',
    },{
      	  xtype: 'textfield', 
          name: 'technicalFouls',
          fieldLabel: 'Technical Fouls:',
    },{
      	  xtype: 'textarea', 
          name: 'comments',
          fieldLabel: 'Comments:',
    },{
      	  xtype: 'textarea', 
          name: 'path',
          fieldLabel: 'Path:',
    },
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
                window.location = "/frcscout/scout/scout.jsp";
            }
        }]
    }];
}
