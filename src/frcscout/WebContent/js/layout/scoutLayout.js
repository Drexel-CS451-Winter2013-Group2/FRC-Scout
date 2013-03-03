function getMatchRecordStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'user', 'team_id', 'match_id', 'color', 'auton_top',
                     'auton_middle', 'auton_bottom', 'teleop_top', 'teleop_middle',
                     'teleop_bottom', 'teleop_pyramid', 'pyramid_level', 'play_style',
                     'confidence', 'ability', 'fouls', 'technical_fouls', 'comments', 'path'],
            data: matchRecordJSON
        });
        return store1;
    }

function getContentItems(){
	return [{
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        defaults: {
            border: 0,
            padding: 0
        },
        items: [{
            xtype: 'buttongroup',
            columns: 3,
            items: [{
                text: 'Add',
                handler: function() {
                    window.location = "/frcscout/scout/addMatchRecord.jsp";
                }
            }, {
                text: 'Edit',
                handler: function() {
                	// TODO do checking here?
                    var a = Ext.getCmp('matchRecordGrid').getSelectionModel().getSelection()[0].data.id;
                    window.location="/frcscout/scout/editMatchRecord.jsp?id=" + a;
                }
            }, {
                text: 'Delete',
                handler: function() {
                    var a = Ext.getCmp('matchRecordGrid').getSelectionModel().getSelection()[0].data.id;
                    var user = Ext.getCmp('matchRecordGrid').getSelectionModel().getSelection()[0].data.user;
                    // TODO do checking here?
                    if (false) {
                        Ext.MessageBox.alert('Error', 'You cannot delete this Match Record.');
                    } else {
                        Ext.MessageBox.confirm('Confirm', 'Are you sure you want delete this Match Record?', 
                                function(btn) { 
                                    if (btn == "yes") {
                            window.location = "/frcscout/scout/deleteMatchRecord.jsp?id=" + a;}});
                    }
                }
            }]
        }, {
            xtype: 'gridpanel',
            title: 'Match Records',
            id: 'matchRecordGrid',
            store: getMatchRecordStore(),
            columns: [
                { dataIndex: 'id', hidden: true},
                { header: 'User',  dataIndex: 'user', flex: 1  },
                { header: 'Team', dataIndex: 'team_id'},
                { header: 'Match Id', dataIndex: 'match_id' },
                { header: 'Color', dataIndex: 'color'},
                { header: 'Auton Top', dataIndex: 'auton_top'},
                { header: 'Auton Middle', dataIndex: 'auton_middle'},
                { header: 'Auton Bottom', dataIndex: 'auton_bottom'},
                { header: 'Teleop Top', dataIndex: 'teleop_top'},
                { header: 'Teleop Middle', dataIndex: 'teleop_middle'},
                { header: 'Teleop Bottom', dataIndex: 'teleop_bottom'},
                { header: 'Teleop Pyramid', dataIndex: 'teleop_pyramid'},
                { header: 'Pyramid Level', dataIndex: 'pyramid_level'},
                { header: 'Play Style', dataIndex: 'play_style'},
                { header: 'Confidence', dataIndex: 'confidence'},
                { header: 'Ability', dataIndex: 'ability'},
                { header: 'Fouls', dataIndex: 'fouls'},
                { header: 'Techincal Fouls', dataIndex: 'technical_fouls'},
                { header: 'Comments', dataIndex: 'comments', hidden: true},
                { header: 'Path', dataIndex: 'path', hidden: true}
                
            ],
            height: 200,
            width: 400
        }]
    }];
}