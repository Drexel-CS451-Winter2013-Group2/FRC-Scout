    function getOverviewStore() { 
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'total_points', 'autonomous', 'teleop', 'climb'],
            sorters: ['id', 'total_points'],
            data: overviewTableJSON
        });
        return store1;
    }
    
    function getRedAllianceStore() {
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'team_id', 'total_points', 'autonomous', 'teleop', 'climb'],
            data: redTeamTableJSON
        });
        return store1;
    }
    
    function getBlueAllianceStore() {
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'team_id', 'total_points', 'autonomous', 'teleop', 'climb'],
            data: blueTeamTableJSON
        });
        return store1;
    }
    
    function getOverviewChartStore() { 
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'total_points', 'autonomous', 'teleop', 'climb'],
            sorters: ['id', 'total_points'],
            data: overviewChartJSON
        });
        return store1;
    }
    
    function getTeamDataStore() {
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'match_id', 'total_points', 'autonomous', 'teleop', 'climb'],
            data: teamDataJSON
        });
        return store1;
    }
    
    function getTeamPieChartStore() {
        var store2 = Ext.create('Ext.data.JsonStore', {
            fields: ['category', 'total'],
            data: teamPieChartJSON
        });
        return store2;
    }
    
    function getEventValue() {
        if (selectedEvent > 0) 
        { return selectedEvent;}
        else {
            return "";
        }
    }

function getGroupByEventItems() {
    return [{
        xtype: 'combobox',
        fieldLabel: 'Select Event',
        store: getEventStore(),
        value: getEventValue(),
        queryMode: 'local',
        displayField: 'name',
        valueField: 'id',
        listeners: {
            select: function( combo, records, eOpts){ 
                var x = Ext.getCmp('eventTab').items.indexOf(Ext.getCmp('eventTab').getActiveTab());
                window.location = "/frcscout/index.jsp?event=" + records[0].data.id + '&eventtab=' + x; 
            }
        }
    }, {
        xtype: 'tabpanel',
        activeTab: parseInt(getURLParameter('eventtab')),
        id: 'eventTab',
        tabPosition: 'top',
        items: [{
            title: 'Overview',
            items: getOverviewItems()
        },{
            title: 'View Match',
            items: getViewMatchItems()
        },{
            title: 'View Team',
            items: getViewTeamItems()
        }]
    }];
}

function getOverviewItems() {
    var overviewChart = {
        xtype: 'chart',
        animate: 'true',
        store: getOverviewChartStore(),
        legend: {
            position: 'bottom'
        },
        axes: [{
            type: 'Numeric',
            position: 'bottom',
            fields: ['autonomous', 'teleop', 'climb'],
            grid: true
        }, {
            type: 'Category',
            position: 'left',
            fields: ['id'],
            title: false
        }],
        series: [{
            type: 'bar',
            axis: 'bottom',
            gutter: 10,
            xField: 'team_number',
            yField: ['autonomous', 'teleop', 'climb'],
            stacked: true,
            tips: {
                trackMouse: true,
                renderer: function(storeItem, item) {
                    this.setTitle(String(item.value[1]));
                }
            }
        }],
        width: 300,
        minHeight: 300
    };
    
    overviewChart.store.sort([{property: 'total_points', direction: 'ASC'}]);
    
    return [{
        layout: {
            type: 'hbox',
            align: 'left'
        },
        items: [{
            xtype: 'gridpanel',
            id: 'eventOverviewGrid',
            store: getOverviewStore(),
            columns: [
                { header: 'Team #',  dataIndex: 'id', flex: 1},
                { header: 'Total Points', dataIndex: 'total_points'},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Disc Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
            ],
            height: 400,
            width: 500, 
            margin: 10
        }, overviewChart]
    }];
}

function getMatchTitle() {
    if (selectedMatch > 0) {
        return '<h1>Match ' + selectedMatch + '</h1>';
    }
    return '<h1>Select match by search</h1>';
}

function getSelectedMatch() {
    if (selectedMatch > 0) {
        return selectedMatch;
    }
    return "";
}

function getViewMatchItems() {
    return [{
        defaultType: 'container',
        layout: {
            type: 'vbox',
            align: 'left',
        },
        items: [{
            layout: {
                type: 'hbox'
            },
            items: [{
                xtype: 'panel',
                border: false,
                html: getMatchTitle(),
                margin: 10,
            }, {
                xtype: 'form',
                border: false,
                layout: 'hbox',
                items: [{
                    xtype: 'combobox',
                    fieldLabel: 'Select Match',
                    store: getMatchStore(),
                    value: getSelectedMatch(),
                    queryMode: 'local',
                    displayField: 'id',
                    valueField: 'id',
                    listeners: {
                        select: function( combo, records, eOpts){ 
                            window.location = '/frcscout/index.jsp?match=' + records[0].data.id + '&eventtab=1';
                        }
                    }
                }],
                margin: '10 0 10 120'
            }]
        }, {
            xtype: 'gridpanel',
            defaultType: 'container',
            title: 'Red Alliance',
            id: 'redAllianceGrid',
            store: getRedAllianceStore(),
            columns: [
                { header: 'Team #',  dataIndex: 'team_id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
            ],
            width: 500,
            margin: 10
        }, {
            xtype: 'button',
            text: 'View Match Record',
            handler: function() {
                var a = Ext.getCmp('redAllianceGrid').getSelectionModel().getSelection()[0].data.id;
                window.location="/frcscout/viewMatchRecord.jsp?id=" + a + "&return=index.jsp?eventtab=1";
            },
            margin: '0 0 10 400'
        }, {
            xtype: 'gridpanel',
            defaultType: 'container',
            title: 'Blue Alliance',
            id: 'blueAllianceGrid',
            store: getBlueAllianceStore(),
            columns: [
                { header: 'Team #',  dataIndex: 'team_id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
            ],
            width: 500,
            margin: 10
        }, {
            xtype: 'button',
            text: 'View Match Record',
            handler: function() {
                var a = Ext.getCmp('blueAllianceGrid').getSelectionModel().getSelection()[0].data.id;
                window.location="/frcscout/viewMatchRecord.jsp?id=" + a + "&return=index.jsp?eventtab=1";
            },
            margin: '0 0 10 400'
        }]
    }];
}

function getTeamTitle() {
    if (selectedTeam > 0) {
        return '<h1>Team ' + selectedTeam + '</h1>';
    }
    return '<h1>Select team by search</h1>';
}

function getSelectedTeam() {
    if (selectedTeam > 0) {
        return selectedTeam;
    }
    return "";
}

function getViewTeamItems() {
    return [{
        defaultType: 'container',
        layout: {
            type: 'vbox',
            align: 'left'
        },
        items: [{
            margin: 10,
            layout: {
                type: 'hbox'
            },
            items: [{
                xtype: 'panel',
                border: false,
                margin: '10 0 0 0',
                html: getTeamTitle(),
            }, {
                xtype: 'form',
                border: false,
                layout: 'hbox',
                items: [{
                    xtype: 'combobox',
                    fieldLabel: 'Select Team',
                    store: getTeamStore(),
                    value: getSelectedTeam(),
                    queryMode: 'local',
                    displayField: 'id',
                    valueField: 'id',
                    listeners: {
                        select: function( combo, records, eOpts){ 
                            window.location = '/frcscout/index.jsp?team=' + records[0].data.id + '&eventtab=1';
                        }
                    }
                }],
                margin: '10 0 10 120'
            }]
        }, {
            xtype: 'gridpanel',
            title: 'Match Records',
            id: 'teamMatchGrid',
            store: getTeamDataStore(),
            columns: [
                { header: 'Match #',  dataIndex: 'match_id', flex: 1},
                { header: 'Auton. Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
            ],
            width: 500,
            margin: '0 0 0 10',
        }, {
            xtype: 'button',
            text: 'View Match Record',
            handler: function() {
                var a = Ext.getCmp('teamMatchGrid').getSelectionModel().getSelection()[0].data.id;
                window.location="/frcscout/viewMatchRecord.jsp?id=" + a + "&return=index.jsp?eventtab=2";
            },
            margin: '10 0 0 400'
        }, {
            layout: {
                type: 'hbox'
            }, 
            items: [{
                xtype: 'chart',
                animate: true,
                width: 500,
                height: 300,
                margin: 10,
                store: getTeamDataStore(),
                legend: {
                    position: 'bottom'
                },
                axes: [{
                    type: 'Numeric',
                    position: 'left',
                    grid: true,
                    minimum: 0
                }, {
                    type: 'Category',
                    position: 'bottom',
                    fields: ['match_id'],
                    title: 'Match #'
                }],
                series: [{
                    type: 'line',
                    axis: 'left',
                    xField: 'id',
                    yField: 'total_points'//,
                   // fill: true
                }, {
                    type: 'line',
                    axis: 'left',
                    xField: 'id',
                    yField: 'autonomous'
                }, {
                    type: 'line',
                    axis: 'left',
                    xField: 'id',
                    yField: 'teleop'
                }, {
                    type: 'line',
                    axis: 'left',
                    xField: 'id',
                    yField: 'climb'
                }]
            }, {
                xtype: 'chart',
                animate: true,
                margin: '50',
                width: 200,
                height: 200,
                store: getTeamPieChartStore(),
                series: [{
                    type: 'pie',
                    angleField: 'total',
                    label: {
                        field: 'category',
                        display: 'inside',
                        contrast: true,
                        font: '12px Helvetica'
                    }
                }]
            }]
        }]
    }];
}