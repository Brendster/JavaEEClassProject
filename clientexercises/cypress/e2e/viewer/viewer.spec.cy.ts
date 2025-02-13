describe('viewer report test', () => {
    it('visits the viewer page and selects an employee and report', () => {
      cy.visit('/');
      cy.get('button').click();
      cy.contains('a', 'viewer').click();
      cy.wait(500); // http call
      cy.get('mat-select[formcontrolname="employeeid"]').click();
      cy.contains('Abbott').click();
      cy.wait(500); // http call
      cy.get('mat-select[formcontrolname="reportid"]').click({ force: true });
      cy.contains('1').click({ force: true });
      cy.contains('created on');
      //cy.get('button').contains('View PDF').click();
    });
  });
  