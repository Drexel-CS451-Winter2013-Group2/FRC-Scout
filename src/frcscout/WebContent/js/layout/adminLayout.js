    function getEventStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location', 'start_date', 'end_date'],
            data: eventJSON
        });
        return store1;
    }
    
    function getUserStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'email', 'first_name', 'last_name', 'role'],
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
        defaults: {
            border: 0
        },
        items: [{
            title: 'User Accounts',
            defaults: {
                border: 0
            },
            items: getUserItems()
        },{
            title: 'Teams',
            defaults: {
                border: 0
            },
            items: getTeamItems()
        },{
            title: 'Events',
            defaults: {
                border: 0
            },
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
                text: 'Add',
                handler: function() {
                    window.location = "/frcscout/admin/addUser.jsp";
                }
            }, {
                text: 'Edit',
                handler: function() {
                    var a = Ext.getCmp('userGrid').getSelectionModel().getSelection()[0].data.id;
                    window.location="/frcscout/admin/editUser.jsp?id=" + a;
                }
            }, {
                text: 'Delete',
                handler: function() {
                    var a = Ext.getCmp('userGrid').getSelectionModel().getSelection()[0].data.id;
                    Ext.MessageBox.confirm('Confirm', 'Are you sure you want delete this user?<br> This action cannot be undone.', 
                                function(btn) { 
                                    if (btn == "yes") {
                            window.location = "/frcscout/admin/deleteUser.jsp?id=" + a;}});
                }
            }]
        }, {
            xtype: 'gridpanel',
            title: 'Example',
            id: 'userGrid',
            store: getUserStore(),
            columns: [
                { dataIndex: 'id', hidden: true},
                { header: 'Email',  dataIndex: 'email', flex: 1  },
                { header: 'First Name', dataIndex: 'first_name'},
                { header: 'Last Name', dataIndex: 'last_name' },
                { header: 'Role', dataIndex: 'role'}
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
                text: 'Add',
                handler: function() {
                    window.location = "/frcscout/admin/addTeam.jsp";
                }
            }, {
                text: 'Edit',
                handler: function() {
                    var a = Ext.getCmp('teamGrid').getSelectionModel().getSelection()[0].data.id;
                    window.location="/frcscout/admin/editTeam.jsp?id=" + a;
                }
            }, {
                text: 'Delete',
                handler: function() {
                    var a = Ext.getCmp('teamGrid').getSelectionModel().getSelection()[0].data.id;
                    Ext.MessageBox.confirm('Confirm', 'Are you sure you want to do that?', 
                                function(btn) { 
                                    if (btn == "yes") {
                            window.location = "/frcscout/admin/deleteTeam.jsp?id=" + a;}});
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
                text: 'Add',
                handler: function() {
                    window.location = "/frcscout/admin/addEvent.jsp";
                }
            }, {
                text: 'Edit',
                handler: function() {
                    var a = Ext.getCmp('eventGrid').getSelectionModel().getSelection()[0].data.id;
                    window.location="/frcscout/admin/editEvent.jsp?id=" + a;
                }
            }, {
                text: 'Delete',
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
                { dataIndex: 'id', hidden: true},
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
