    function getEventStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location', 'start_date', 'end_date'],
            data: eventJSON
        });
        return store1;
    }
    
    function getUserStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['email', 'first_name', 'last_name'],
            data: userJSON
        });
        return store1;
    }
    function getTeamStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location'],
            data: teamJSON
        });
        return store1;
    }


function getContentItems(){
    return [{
        xtype: 'tabpanel',
        activeTab: parseInt(getURLParameter('tab')),
        tabPosition: 'top',
        items: [{
            title: 'User Accounts',
            items: getUserItems()
        },{
            title: 'Teams',
            items: getTeamItems()
        },{
            title: 'Events',
            items: getEventItems()
        }]
    }];
}

function getUserItems() {
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
                text: 'add',
                handler: function() {
                    alert('You clicked the button!');
                }
            }, {
                text: 'edit',
                handler: function() {
                    alert('You clicked the button!');
                }
            }, {
                text: 'delete',
                handler: function() {
                    alert('You clicked the button!');
                }
            }]
        }, {
            xtype: 'gridpanel',
            title: 'Example',
            id: 'userGrid',
            store: getUserStore(),
            columns: [
                { header: 'Email',  dataIndex: 'email', flex: 1  },
                { header: 'First Name', dataIndex: 'first_name'},
                { header: 'Last Name', dataIndex: 'last_name' }
            ],
            height: 200,
            width: 400
        }]
    }];
}
function getTeamItems() {
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
                text: 'add',
                handler: function() {
                    alert('You clicked the button!');
                }
            }, {
                text: 'edit',
                handler: function() {
                    alert('You clicked the button!');
                }
            }, {
                text: 'delete',
                handler: function() {
                    alert('You clicked the button!');
                }
            }]
        }, {
            xtype: 'gridpanel',
            title: 'Example',
            id: 'teamGrid',
            store: getTeamStore(),
            columns: [
                { header: 'Number',  dataIndex: 'id', flex: 1  },
                { header: 'Name', dataIndex: 'name'},
                { header: 'Location', dataIndex: 'location' }
            ],
            height: 200,
            width: 400
        }]
    }];
}
function getEventItems() {
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
                text: 'add',
                handler: function() {
                    window.location = "/frcscout/admin/addEvent.jsp";
                }
            }, {
                text: 'edit',
                handler: function() {
                    var a = Ext.getCmp('eventGrid').getSelectionModel().getSelection()[0].data.id;
                    window.location="/frcscout/admin/editEvent.jsp?id=" + a;
                }
            }, {
                text: 'delete',
                handler: function() {
                    var a = Ext.getCmp('eventGrid').getSelectionModel().getSelection()[0].data.id;
                    Ext.MessageBox.confirm('Confirm', 'Are you sure you want to do that?', 
                                function(btn) { 
                                    if (btn == "yes") {
                            window.location = "/frcscout/admin/deleteEvent.jsp?id=" + a;}});
                }
            }]
        }, {
            xtype: 'gridpanel',
            title: 'Example',
            store: getEventStore(),
            id: 'eventGrid',
            columns: [
                { dataIndex: 'id', dataIndex: 'id', hidden: true},
                { header: 'Name',  dataIndex: 'name', flex: 1  },
                { header: 'Location', dataIndex: 'location'},
                { header: 'Start Date', dataIndex: 'start_date' },
                { header: 'End Date', dataIndex: 'end_date'}
            ],
            height: 200,
            width: 400
        }]
    }];
}
